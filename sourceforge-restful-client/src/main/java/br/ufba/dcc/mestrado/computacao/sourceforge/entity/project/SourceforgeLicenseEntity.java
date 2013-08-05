package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeLicenseEntity.NODE_NAME)
public class SourceforgeLicenseEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3617230824829578481L;

	public final static String NODE_NAME = "license";

	@Column(name = "name")
	private String name;

	@Column(name = "url")
	private String url;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
