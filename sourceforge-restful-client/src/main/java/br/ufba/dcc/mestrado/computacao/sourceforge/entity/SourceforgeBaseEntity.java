package br.ufba.dcc.mestrado.computacao.sourceforge.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class SourceforgeBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1172528122380283680L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	
}
