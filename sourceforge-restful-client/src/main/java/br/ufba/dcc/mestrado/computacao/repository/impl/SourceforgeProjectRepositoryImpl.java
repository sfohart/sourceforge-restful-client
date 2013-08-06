package br.ufba.dcc.mestrado.computacao.repository.impl;

import br.ufba.dcc.mestrado.computacao.qualifier.SourceforgeProjectRepositortyQualifier;
import br.ufba.dcc.mestrado.computacao.repository.SourceforgeProjectRepository;
import br.ufba.dcc.mestrado.computacao.sourceforge.entity.project.SourceforgeProjectEntity;

@SourceforgeProjectRepositortyQualifier
public class SourceforgeProjectRepositoryImpl extends BaseRepositoryImpl<SourceforgeProjectEntity>
		implements SourceforgeProjectRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3998722368418412096L;

	public SourceforgeProjectRepositoryImpl() {
		super(SourceforgeProjectEntity.class);
	}

}
