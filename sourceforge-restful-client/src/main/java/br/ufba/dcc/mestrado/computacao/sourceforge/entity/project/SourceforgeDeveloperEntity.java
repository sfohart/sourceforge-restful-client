package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeDeveloperEntity.NODE_NAME)
public class SourceforgeDeveloperEntity extends SourceforgeBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4127846405643985149L;

	public final static String NODE_NAME = "developer";
	
	@Column(name = "homepage")
	private String homepage;
	
	@Column(name = "mbox_sha1sum")
	private String mboxSHA1Sum;
	
	@Column(name = "name")
	private String name;

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getMboxSHA1Sum() {
		return mboxSHA1Sum;
	}

	public void setMboxSHA1Sum(String mboxSHA1Sum) {
		this.mboxSHA1Sum = mboxSHA1Sum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
