package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeSVNRepositoryEntity.NODE_NAME)
public class SourceforgeSVNRepositoryEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7420423764349013049L;

	public final static String NODE_NAME = "svn_repository";

	@Column(name = "browse")
	private String browse;

	@Column(name = "location")
	private String location;

	public String getBrowse() {
		return this.browse;
	}

	public void setBrowse(String browse) {
		this.browse = browse;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
