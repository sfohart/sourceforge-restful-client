package br.ufba.dcc.mestrado.computacao.service;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

public interface BaseSourceforgeService<DTO extends SourceforgeDTO, E extends SourceforgeBaseEntity> {

	public E store(DTO dto) throws Exception;
	
}
