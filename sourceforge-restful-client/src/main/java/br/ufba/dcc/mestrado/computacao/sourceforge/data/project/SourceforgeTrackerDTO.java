package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeTrackerDTO.NODE_NAME)
public class SourceforgeTrackerDTO implements SourceforgeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3671526502459491024L;

	public final static String NODE_NAME = "tracker";

	@XStreamAlias("location")
	private String location;

	@XStreamAlias("name")
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
