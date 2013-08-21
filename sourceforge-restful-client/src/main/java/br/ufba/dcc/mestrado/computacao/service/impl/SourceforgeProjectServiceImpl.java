package br.ufba.dcc.mestrado.computacao.service.impl;

import javax.inject.Inject;

import br.ufba.dcc.mestrado.computacao.qualifier.SourceforgeProjectRepositortyQualifier;
import br.ufba.dcc.mestrado.computacao.qualifier.SourceforgeProjectServiceQualifier;
import br.ufba.dcc.mestrado.computacao.repository.SourceforgeProjectRepository;
import br.ufba.dcc.mestrado.computacao.service.SourceforgeProjectService;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProjectDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.entity.project.SourceforgeProjectEntity;

@SourceforgeProjectServiceQualifier
public class SourceforgeProjectServiceImpl extends BaseSourceforgeServiceImpl<SourceforgeProjectDTO, SourceforgeProjectEntity>
		implements SourceforgeProjectService {

	@Inject
	@SourceforgeProjectRepositortyQualifier
	private SourceforgeProjectRepository projectRepository;
	
	public SourceforgeProjectServiceImpl() {
		super(SourceforgeProjectDTO.class, SourceforgeProjectEntity.class);
	}

	@Override
	public SourceforgeProjectEntity store(SourceforgeProjectDTO dto)
			throws Exception {
		SourceforgeProjectEntity entity = super.store(dto);
		projectRepository.save(entity);
		return entity;
	}
	
}
