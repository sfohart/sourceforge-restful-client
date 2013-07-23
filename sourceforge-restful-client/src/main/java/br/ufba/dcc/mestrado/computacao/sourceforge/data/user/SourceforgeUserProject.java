package br.ufba.dcc.mestrado.computacao.sourceforge.data.user;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeUserProject.NODE_NAME)
public class SourceforgeUserProject {

	public final static String NODE_NAME = "project";

	@XStreamAlias("id")
	private Long id;

	@XStreamAlias("name")
	private String name;

	@XStreamAlias("role")
	private String role;

	@XStreamAlias("unix_name")
	private String unixName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUnixName() {
		return unixName;
	}

	public void setUnixName(String unixName) {
		this.unixName = unixName;
	}

}
