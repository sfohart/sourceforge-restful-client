package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeLicenseDTO.NODE_NAME)
public class SourceforgeLicenseDTO implements SourceforgeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4974885445291196355L;

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
