package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import java.io.BufferedInputStream;
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
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeCharity;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeDeveloper;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeDonation;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeLicense;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProject;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeSVNRepository;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeTracker;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUser;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUserProject;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class SourceforgeRestfulClient {

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
	
	public SourceforgeProject getProjectById(String projectId) {
		SourceforgeProject sourceforgeProject = null;
		
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
	
	public SourceforgeProject getProjectByName(String projectName) {
		SourceforgeProject resource = null;
		
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
	
	public SourceforgeUser getUserByName(String username) {
		SourceforgeUser resource = null;
		
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
	
	public SourceforgeUser getUserById(String userId) {
		SourceforgeUser resource = null;
		
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
						SourceforgeUser.class,
						SourceforgeUserProject.class
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
						SourceforgeCharity.class,
						SourceforgeDeveloper.class,
						SourceforgeDonation.class,
						SourceforgeLicense.class,
						SourceforgeProject.class,
						SourceforgeSVNRepository.class,
						SourceforgeTracker.class
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
