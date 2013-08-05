package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeDatabaseEntity.NODE_NAME)
public class SourceforgeDatabaseEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5296183440224795553L;

	public final static String NODE_NAME = "database";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
