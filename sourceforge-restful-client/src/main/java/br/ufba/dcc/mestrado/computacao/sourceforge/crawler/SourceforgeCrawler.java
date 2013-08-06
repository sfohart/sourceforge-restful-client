package br.ufba.dcc.mestrado.computacao.sourceforge.crawler;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import br.ufba.dcc.mestrado.computacao.qualifier.SourceforgeProjectRepositortyQualifier;
import br.ufba.dcc.mestrado.computacao.repository.SourceforgeProjectRepository;
import br.ufba.dcc.mestrado.computacao.sourceforge.restful.client.SourceforgeRestfulClient;

public class SourceforgeCrawler {
	
	public static Logger logger = Logger.getLogger(SourceforgeCrawler.class);
	
	@Inject
	private SourceforgeRestfulClient sourceforgeRestfulClient;
	
	@Inject
	@SourceforgeProjectRepositortyQualifier
	private SourceforgeProjectRepository projectRepository;
	
	public SourceforgeRestfulClient getSourceforgeRestfulClient() {
		return sourceforgeRestfulClient;
	}

	public void setSourceforgeRestfulClient(
			SourceforgeRestfulClient sourceforgeRestfulClient) {
		this.sourceforgeRestfulClient = sourceforgeRestfulClient;
	}

	public SourceforgeProjectRepository getProjectRepository() {
		return projectRepository;
	}

	public void setProjectRepository(SourceforgeProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public static void main(String[] args) throws Exception {
		WeldContainer container = new Weld().initialize();
		SourceforgeCrawler crawler = container.instance().select(SourceforgeCrawler.class).get();
		
		crawler.getProjectRepository().findAll();
	}

}