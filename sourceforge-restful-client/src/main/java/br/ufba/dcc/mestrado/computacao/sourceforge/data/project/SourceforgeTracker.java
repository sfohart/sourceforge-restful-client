package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeTracker.NODE_NAME)
public class SourceforgeTracker {

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
