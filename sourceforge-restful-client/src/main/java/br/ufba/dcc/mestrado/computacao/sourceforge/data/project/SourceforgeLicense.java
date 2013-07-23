package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeLicense.NODE_NAME)
public class SourceforgeLicense {

	public final static String NODE_NAME = "license";

	@XStreamAlias("name")
	private String name;

	@XStreamAlias("url")
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
