package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.RestClient;
import br.com.caelum.restfulie.Restfulie;
import br.com.caelum.restfulie.mediatype.JsonMediaType;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeCharityDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeDeveloperDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeDonationDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeLicenseDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProjectDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeSVNRepositoryDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeTrackerDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUserDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUserProjectDTO;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.DOAP;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class SourceforgeRestfulClient {
	
	public static final class SOURCEFORGE {
		private static final String SOURCEFORGE_NS = "http://sourceforge.net/api/sfelements.rdf#";

		private static final Model model = ModelFactory.createDefaultModel();
		
		public static final Property id = model.createProperty(SOURCEFORGE_NS, "id");
		public static final Property feature = model.createProperty(SOURCEFORGE_NS, "feature");
		public static final Property baseUrl = model.createProperty(SOURCEFORGE_NS, "base-url");
		public static final Property typeNumber = model.createProperty(SOURCEFORGE_NS, "type");
		public static final Property privateNumber = model.createProperty(SOURCEFORGE_NS, "private");
		public static final Property percentile = model.createProperty(SOURCEFORGE_NS, "percentile");
		public static final Property ranking = model.createProperty(SOURCEFORGE_NS, "ranking");
		public static final Property fileFeed = model.createProperty(SOURCEFORGE_NS, "file-feed");
	}
	
	

	Logger logger = Logger.getLogger(SourceforgeRestfulClient.class.getName());
	
	private String apiKey;
	private static Properties properties;
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public static Properties getProperties() throws IOException {
		if (properties == null) {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sourceforge.properties");
			
			properties = new Properties();
			properties.load(is);
		}
		
		return properties;
	}
	
	public void getProjectSince(String since) throws IOException, IllegalArgumentException, FeedException {
		String url = getProperties().getProperty("sourceforge.api.project.index.new_since");
		String uri = MessageFormat.format(url, since);
		
		 SyndFeedInput input = new SyndFeedInput();
		 URL feedSource = new URL(uri);
		 SyndFeed feed = input.build(new XmlReader(feedSource));
		 
		 List<SyndEntry> entries = feed.getEntries();
		 if (entries != null && ! entries.isEmpty()) {
			 for (SyndEntry entry : entries) {
				 SourceforgeProjectDTO projectDTO = createProject(entry.getLink());
			 }
		 }
	}
	
	protected SourceforgeProjectDTO createProject(String doap) {
		Model model = TDBFactory.createDataset().getDefaultModel();
		model.setNsPrefix("doap", DOAP.getURI());
		
		model.read(doap, null);
		System.out.println(doap);
		System.out.println();
		model.write(System.out);
		
		Resource projectRDF = model.getResource(doap + "#");
		
		SourceforgeProjectDTO projectDTO = new SourceforgeProjectDTO();
		
		//name property
		if (projectRDF.getProperty(DOAP.name) != null) {
			projectDTO.setName(projectRDF.getProperty(DOAP.name).getString());
		}
		
		//created property
		if (projectRDF.getProperty(DOAP.created) != null) {
			projectDTO.setCreated(projectRDF.getProperty(DOAP.created).getString());
		}
		
		//id property		
		if (projectRDF.getProperty(SOURCEFORGE.id) != null) {
			String id = projectRDF.getProperty(SOURCEFORGE.id).getString();
			if (id != null && ! "".equals(id)) {
				projectDTO.setId(Long.valueOf(id));
			}
		}
		
		//homepage property
		if (projectRDF.getProperty(DOAP.homepage) != null) {
			if (projectRDF.getProperty(DOAP.homepage).getObject() != null) {
				projectDTO.setHomepage(projectRDF.getProperty(DOAP.homepage).getObject().toString());
			}
		}
		
		//download-page property
		if (projectRDF.getProperty(DOAP.download_page) != null) {
			if (projectRDF.getProperty(DOAP.download_page).getObject() != null) {
				projectDTO.setDownloadPage(projectRDF.getProperty(DOAP.download_page).getObject().toString());
			}
		}
		
		//download-mirror property
		if (projectRDF.getProperty(DOAP.shortdesc) != null) {
			projectDTO.setShortDesc(projectRDF.getProperty(DOAP.shortdesc).getString());
		}
		
		//base-url property
		if (projectRDF.getProperty(SOURCEFORGE.baseUrl) != null) {
			if (projectRDF.getProperty(SOURCEFORGE.baseUrl).getObject() != null) {
				projectDTO.setBaseUrl(projectRDF.getProperty(SOURCEFORGE.baseUrl).getObject().toString());
			}
		}
		
		//type property
		if (projectRDF.getProperty(SOURCEFORGE.typeNumber) != null) {
			String typeNumber = projectRDF.getProperty(SOURCEFORGE.typeNumber).getString();
			if (typeNumber != null && ! "".equals(typeNumber)) {
				projectDTO.setTypeNumber(Long.valueOf(typeNumber));
			}
		}
		
		//private property
		if (projectRDF.getProperty(SOURCEFORGE.privateNumber) != null) {
			String privateNumber = projectRDF.getProperty(SOURCEFORGE.privateNumber).getString();
			if (privateNumber != null && ! "".equals(privateNumber)) {
				projectDTO.setPrivateNumber(Long.valueOf(privateNumber));
			}
		}
		
		
		//percentile property
		if (projectRDF.getProperty(SOURCEFORGE.percentile) != null) {
			String percentile = projectRDF.getProperty(SOURCEFORGE.percentile).getString();
			if (percentile != null && ! "".equals(percentile)) {
				projectDTO.setPercentile(Double.valueOf(percentile));
			}
		}
		
		//ranking property
		if (projectRDF.getProperty(SOURCEFORGE.ranking) != null) {
			String ranking = projectRDF.getProperty(SOURCEFORGE.ranking).getString();
			if (ranking != null && ! "".equals(ranking)) {
				projectDTO.setRanking(Double.valueOf(ranking));
			}
		}
		
		//feature property
		StmtIterator featureIterator = projectRDF.listProperties(SOURCEFORGE.feature);
		while (featureIterator.hasNext()) {
			Statement featureStmt = featureIterator.next();
			Property featurePredicate = featureStmt.getPredicate();
			RDFNode featureObject = featureStmt.getObject();
			
			String featureName = featureObject.asResource().getProperty(DOAP.name).getString();
			System.out.println(featureName);
		}
		
		return projectDTO;
	}
	
	public SourceforgeProjectDTO getProjectById(String projectId) {
		SourceforgeProjectDTO sourceforgeProject = null;
		
		try {
			String url = getProperties().getProperty("sourceforge.api.project.id");			
			String uri = MessageFormat.format(url, projectId);
			
			logger.info(uri);
			
			RestClient restClient = configureProjectMediaTypes();
			
			Response response = restClient.at(uri).accept("application/json").get();
			sourceforgeProject = response.getResource();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sourceforgeProject;
	}
	
	public SourceforgeProjectDTO getProjectByName(String projectName) {
		SourceforgeProjectDTO resource = null;
		
		try {
			String url = getProperties().getProperty("sourceforge.api.project.name");			
			String uri = MessageFormat.format(url, projectName);
			
			logger.info(uri);
			
			RestClient restClient = configureProjectMediaTypes();
			
			Response response = restClient.at(uri).accept("application/json").get();
			if (response.getCode() == 200) {
				resource = response.getResource();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resource;
	}
	
	public SourceforgeUserDTO getUserByName(String username) {
		SourceforgeUserDTO resource = null;
		
		try {
			String url = getProperties().getProperty("sourceforge.api.user.username");			
			String uri = MessageFormat.format(url, username);
			
			logger.info(uri);
			
			RestClient restClient = configureUserMediaTypes();
			
			Response response = restClient.at(uri).accept("application/json").get();
			if (response.getCode() == 200) {
				resource = response.getResource();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resource;
	}
	
	public SourceforgeUserDTO getUserById(String userId) {
		SourceforgeUserDTO resource = null;
		
		try {
			String url = getProperties().getProperty("sourceforge.api.user.id");			
			String uri = MessageFormat.format(url, userId);
			
			logger.info(uri);
			
			RestClient restClient = configureUserMediaTypes();
			
			Response response = restClient.at(uri).accept("application/json").get();
			if (response.getCode() == 200) {
				resource = response.getResource();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resource;
	}
	
	/**
	 * @return
	 */
	private RestClient configureUserMediaTypes() {
		RestClient restClient = Restfulie.custom();
		restClient.getMediaTypes().register(new JsonMediaType() {

			@Override
			protected List<Class> getTypesToEnhance() {					
				return Arrays.<Class>asList(
						SourceforgeUserDTO.class,
						SourceforgeUserProjectDTO.class
					);
			}
			
			@Override
			protected List<String> getCollectionNames() {
				return Arrays.asList(
						"projects"
					);
			}
			
		});
		return restClient;
	}
	

	/**
	 * @return
	 */
	private RestClient configureProjectMediaTypes() {
		RestClient restClient = Restfulie.custom();
		restClient.getMediaTypes().register(new JsonMediaType() {

			@Override
			protected List<Class> getTypesToEnhance() {					
				return Arrays.<Class>asList(
						SourceforgeCharityDTO.class,
						SourceforgeDeveloperDTO.class,
						SourceforgeDonationDTO.class,
						SourceforgeLicenseDTO.class,
						SourceforgeProjectDTO.class,
						SourceforgeSVNRepositoryDTO.class,
						SourceforgeTrackerDTO.class
					);
			}
			
			@Override
			protected List<String> getCollectionNames() {
				return Arrays.asList(
						"licenses", 
						"maintainers", 
						"programming-languages", 
						"developers",
						"categories",
						"databases",
						"environments",
						"audiences",
						"topics",
						"os");
			}
			
		});
		return restClient;
	}
	
	
	public void getProjectActivity(String projectId) {
		
		try {
		
			String urlTemplate = getProperties().getProperty("sourceforge.api.event.index.project-id");			
			String uri = MessageFormat.format(urlTemplate, projectId);
			
			URL url = new URL(uri);
			
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(url)); 
			
			List<SyndEntry> entries = feed.getEntries();
			if (entries != null) {
				for (SyndEntry entry : entries) {
					System.out.println(entry.getAuthor());
					System.out.println(entry.getForeignMarkup());
					System.out.println(entry.getLink());
					System.out.println(entry.getTitle());
					System.out.println(entry.getUri());
					System.out.println(entry.getDescription());
					
					if (entry.getContents() != null) {
						
						Model model = ModelFactory.createDefaultModel();
						
						
						System.out.println("Conteúdos:");
						for (Object item : entry.getContents()) {
							SyndContent content = (SyndContent) item;
							
							System.out.println("\t" + content.getMode());
							System.out.println("\t" + content.getType());
							
							if (content.getValue() != null && ! content.getValue().isEmpty()) {
								
								InputStream is = new ByteArrayInputStream(content.getValue().getBytes()); 
								
								model.read(is, null);
								
								String nsDoap = "http://usefulinc.com/ns/doap#";
								
								System.out.println("\t\tNome: " + model.getResource(nsDoap + "name"));
								System.out.println("\t\tDescrição Curta: " + model.getResource(nsDoap + "shortdesc"));
								System.out.println("\t\tID: " + model.getResource(nsDoap + "id"));
							}
							
							System.out.println("\t" + content.getValue());
						}
					}
					
					
					if (entry.getCategories() != null) {
						System.out.println("Categorias:");
						for (Object item : entry.getCategories()) {
							
							SyndCategory category = (SyndCategory) item;
							System.out.println("\t" + category.getName());
						}
					}
					
				}
			}
		
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		}
		
	}
}
