package br.ufba.dcc.mestrado.computacao.sourceforge.crawler;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import br.ufba.dcc.mestrado.computacao.sourceforge.restful.client.SourceforgeRestfulClient;

import com.sun.syndication.io.FeedException;

public class SourceforgeCrawler {

	public static Logger logger = Logger.getLogger(SourceforgeCrawler.class);

	@Inject
	private SourceforgeRestfulClient sourceforgeRestfulClient;

	public SourceforgeRestfulClient getSourceforgeRestfulClient() {
		return sourceforgeRestfulClient;
	}

	public void setSourceforgeRestfulClient(
			SourceforgeRestfulClient sourceforgeRestfulClient) {
		this.sourceforgeRestfulClient = sourceforgeRestfulClient;
	}

	public void run() {
		try {
			sourceforgeRestfulClient.downloadNewProjectSinceTimestamp();
		} catch (IllegalArgumentException | IOException | FeedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		WeldContainer container = new Weld().initialize();
		SourceforgeCrawler crawler = container.instance()
				.select(SourceforgeCrawler.class).get();

		crawler.run();
	}

}
