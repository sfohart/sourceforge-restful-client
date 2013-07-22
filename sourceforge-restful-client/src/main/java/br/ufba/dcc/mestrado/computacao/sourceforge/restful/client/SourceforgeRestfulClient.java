package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.RestClient;
import br.com.caelum.restfulie.Restfulie;
import br.com.caelum.restfulie.mediatype.JsonMediaType;
import br.com.caelum.restfulie.mediatype.XmlMediaType;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProject;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProjectResult;
import br.ufba.dcc.mestrado.computacao.sourceforge.restful.responses.SourceforgeProjectResponse;

public class SourceforgeRestfulClient {

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
			
			RestClient restfulie = Restfulie.custom();
			
			XmlMediaType mediaType = new XmlMediaType();
			mediaType.withTypes(
					SourceforgeProjectResponse.class,
					SourceforgeProjectResult.class,
					SourceforgeProject.class);
						
			restfulie.getMediaTypes().register(mediaType);
			
			Response response = restfulie.at(uri).get();
			sourceforgeProject = response.getResource();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sourceforgeProject;
	}
	
}
