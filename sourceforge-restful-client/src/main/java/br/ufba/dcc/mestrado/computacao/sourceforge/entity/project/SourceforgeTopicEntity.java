package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeTopicEntity.NODE_NAME)
public class SourceforgeTopicEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3264290837440881212L;

	public final static String NODE_NAME = "topic";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
