package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jdom.Content;
import org.jdom.Element;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.RestClient;
import br.com.caelum.restfulie.Restfulie;
import br.com.caelum.restfulie.mediatype.JsonMediaType;
import br.ufba.dcc.mestrado.computacao.qualifier.SourceforgeCrawlerProjectRepositoryQualifier;
import br.ufba.dcc.mestrado.computacao.qualifier.SourceforgeProjectServiceQualifier;
import br.ufba.dcc.mestrado.computacao.repository.SourceforgeCrawlerProjectRepository;
import br.ufba.dcc.mestrado.computacao.service.SourceforgeProjectService;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeCharityDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeDeveloperDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeDonationDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeLicenseDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProjectDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeSVNRepositoryDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeTrackerDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUserDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUserProjectDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeCrawlerProjectEntity;

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
	
	@Inject
	@SourceforgeProjectServiceQualifier
	private SourceforgeProjectService projectService;
	
	@Inject
	@SourceforgeCrawlerProjectRepositoryQualifier
	private SourceforgeCrawlerProjectRepository crawlerRepository;

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
	
	@SuppressWarnings("unchecked")
	public void downloadNewProjectSinceTimestamp() throws IllegalArgumentException, FeedException, IOException  {
		
		SourceforgeCrawlerProjectEntity config = crawlerRepository.findCrawlerConfig();
		if (config == null) {
			config = new SourceforgeCrawlerProjectEntity();
		}
		
		Long limit = config.getLimit();
		Long offset = config.getOffset();
		Long newSince = config.getNewSince();
		
		String baseURL = getProperties().getProperty("sourceforge.api.baseURL");
		
		try {
			
			if (newSince == null) {
				newSince = System.currentTimeMillis();
			}
			
			String url = getProperties().getProperty("sourceforge.api.project.index.new_since");
			String uri = MessageFormat.format(url, newSince);
			
			if (limit != null && offset != null) {
				if (limit > 0 && offset > 0) {
					url = getProperties().getProperty("sourceforge.api.project.index.new_since.link");
					uri = MessageFormat.format(url, newSince, limit, offset);
				}
			}
			
			
			SyndFeedInput input = new SyndFeedInput();
			List<SyndEntry> entries = null;
			
			do {
				
				URL feedSource = new URL(uri);
				SyndFeed feed = input.build(new XmlReader(feedSource));
				
				entries = feed.getEntries();
				
				if (entries != null && ! entries.isEmpty()) {
					for (SyndEntry entry : entries) {
						try {
							SourceforgeProjectDTO projectDTO = createProject(entry.getLink());
							projectService.store(projectDTO);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				//obtendo e salvando próxima página a ser lida
				List<Element> elementList = (List<Element>) feed.getForeignMarkup();
				if (elementList != null && ! elementList.isEmpty()) {
					for (Element element : elementList) {
						if (element.getContent() != null && ! element.getContent().isEmpty()) {
							Content content = element.getContent(0);
							uri = baseURL + content.getValue();
							
							String pattern = "http://sourceforge.net/api/project/index/new_since/(\\d+)/limit/(\\d+)/offset/(\\d+)/rss";
							
							String limitParam 	= uri.replaceAll(pattern, "$2");
							String offsetParam 	= uri.replaceAll(pattern, "$3");
							
							if (limitParam != null && ! "".equals(limitParam)) {
								limit = Long.valueOf(limitParam);
							}
							
							if (offsetParam != null && ! "".equals(offsetParam)) {
								offset = Long.valueOf(offsetParam);
							}
							
							config.setLimit(limit);
							config.setOffset(offset);
							break;
						}
					}
				}
				
				crawlerRepository.save(config);
			} while (entries != null && ! entries.isEmpty());
			
			if (entries == null || entries.isEmpty()) {
				config.setNewSince(System.currentTimeMillis());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected SourceforgeProjectDTO createProject(String doap) {
		Model model = TDBFactory.createDataset().getDefaultModel();
		model.setNsPrefix("doap", DOAP.getURI());
		
		model.read(doap, null);
		
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
						
						
						System.out.println("Conte�dos:");
						for (Object item : entry.getContents()) {
							SyndContent content = (SyndContent) item;
							
							System.out.println("\t" + content.getMode());
							System.out.println("\t" + content.getType());
							
							if (content.getValue() != null && ! content.getValue().isEmpty()) {
								
								InputStream is = new ByteArrayInputStream(content.getValue().getBytes()); 
								
								model.read(is, null);
								
								String nsDoap = "http://usefulinc.com/ns/doap#";
								
								System.out.println("\t\tNome: " + model.getResource(nsDoap + "name"));
								System.out.println("\t\tDescri��o Curta: " + model.getResource(nsDoap + "shortdesc"));
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
