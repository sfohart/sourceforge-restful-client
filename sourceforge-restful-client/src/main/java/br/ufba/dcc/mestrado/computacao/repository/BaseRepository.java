package br.ufba.dcc.mestrado.computacao.repository;

import java.io.Serializable;
import java.util.List;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

public interface BaseRepository<E extends SourceforgeBaseEntity> extends Serializable {

	public Long countAll();
	public List<E> findAll();	
	public List<E> findAll(String orderBy);
	public List<E> findAll(Integer startAt, Integer offset, String orderBy);
	
	public E findById(Long id);
	public void save(E entity) throws Exception;
	public void add(E entity) throws Exception;
	public void update(E entity) throws Exception;
	public void delete(E entity)  throws Exception;
	
}
