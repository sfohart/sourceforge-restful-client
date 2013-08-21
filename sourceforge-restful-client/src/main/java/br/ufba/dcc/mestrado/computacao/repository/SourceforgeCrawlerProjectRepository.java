package br.ufba.dcc.mestrado.computacao.repository;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeCrawlerProjectEntity;

public interface SourceforgeCrawlerProjectRepository extends BaseRepository<SourceforgeCrawlerProjectEntity>{
	
	public SourceforgeCrawlerProjectEntity findCrawlerConfig();
	
}
