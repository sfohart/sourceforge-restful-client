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
			
			Response response = restClient.at(uri).accept("application/json").get();
			sourceforgeProject = response.getResource();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sourceforgeProject;
	}
	
}
