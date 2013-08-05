package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeTrackerEntity.NODE_NAME)
public class SourceforgeTrackerEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -489631867751745286L;

	public final static String NODE_NAME = "tracker";

	@Column(name = "location")
	private String location;

	@Column(name = "name")
	private String name;

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
