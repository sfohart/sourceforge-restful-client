package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeSVNRepositoryEntity.NODE_NAME)
public class SourceforgeSVNRepositoryEntity {

	public final static String NODE_NAME = "SVNRepository";

	@XStreamAlias("browse")
	private String browse;

	@XStreamAlias("location")
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
