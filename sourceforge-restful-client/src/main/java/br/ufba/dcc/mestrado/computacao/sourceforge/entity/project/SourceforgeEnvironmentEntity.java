package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeEnvironmentEntity.NODE_NAME)
public class SourceforgeEnvironmentEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7569097511824681699L;

	public final static String NODE_NAME = "environment";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
