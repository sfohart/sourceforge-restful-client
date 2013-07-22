package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.BeforeClass;


public class AbstractSourceforgeApiTest {
	
	private static Properties properties;
	private static String apiKey;
	private static SourceforgeRestfulClient sourceforgeRestfulClient;	

	public AbstractSourceforgeApiTest() {
		super();
	}
	
	public static Properties getProperties() {
		return properties;
	}

	public static String getApiKey() {
		return apiKey;
	}
	
	public static SourceforgeRestfulClient getSourceforgeRestfulClient() {
		return sourceforgeRestfulClient;
	}

	@BeforeClass
	public static void beforeTest() throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sourceforge.properties");
		
		properties = new Properties();
		properties.load(is);
		
		apiKey = properties.getProperty("test.sourceforge.api.key");
		
		sourceforgeRestfulClient = new SourceforgeRestfulClient();
		sourceforgeRestfulClient.setApiKey(apiKey);
	}
	

}
