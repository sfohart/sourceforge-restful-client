package br.ufba.dcc.mestrado.computacao.repository.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.ufba.dcc.mestrado.computacao.qualifier.SourceforgeCrawlerProjectRepositoryQualifier;
import br.ufba.dcc.mestrado.computacao.repository.SourceforgeCrawlerProjectRepository;
import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeCrawlerProjectEntity;

@SourceforgeCrawlerProjectRepositoryQualifier
public class SourceforgeCrawlerProjectRepositoryImpl extends BaseRepositoryImpl<SourceforgeCrawlerProjectEntity>
	implements SourceforgeCrawlerProjectRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7801826722021443632L;

	public SourceforgeCrawlerProjectRepositoryImpl() {
		super(SourceforgeCrawlerProjectEntity.class);
	}

	@Override
	public SourceforgeCrawlerProjectEntity findCrawlerConfig() {		
		TypedQuery<SourceforgeCrawlerProjectEntity> crawlerConfig = super.createSelectAllQuery();
		
		SourceforgeCrawlerProjectEntity entity = null;
		
		try {
			entity = crawlerConfig.getSingleResult();
		} catch (NoResultException ex) {
		}
		
		return entity;
	}

}
