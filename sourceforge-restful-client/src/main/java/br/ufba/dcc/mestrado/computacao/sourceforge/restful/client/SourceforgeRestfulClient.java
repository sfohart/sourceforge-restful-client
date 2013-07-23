package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.RestClient;
import br.com.caelum.restfulie.Restfulie;
import br.com.caelum.restfulie.mediatype.JsonMediaType;
import br.com.caelum.restfulie.mediatype.XmlMediaType;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeCharity;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeDeveloper;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeDonation;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeLicense;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProject;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeSVNRepository;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeTracker;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUser;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUserProject;

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
	
}
