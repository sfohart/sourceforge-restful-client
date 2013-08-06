package br.ufba.dcc.mestrado.computacao.sourceforge.data;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SourceforgeSingleValueDTO implements SourceforgeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1702887183857067990L;
	
	@XStreamAlias("name")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
