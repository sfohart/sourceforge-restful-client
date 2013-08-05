package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeOSEntity.NODE_NAME)
public class SourceforgeOSEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2258329076662407259L;

	public final static String NODE_NAME = "os";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
