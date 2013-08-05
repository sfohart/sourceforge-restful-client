package br.ufba.dcc.mestrado.computacao.sourceforge.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;
import br.ufba.dcc.mestrado.computacao.sourceforge.entity.project.SourceforgeProjectEntity;

@Entity
@Table(name = SourceforgeUserProjectEntity.NODE_NAME)
public class SourceforgeUserProjectEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3715790833883160319L;

	public final static String NODE_NAME = "user_project_info";

	@OneToOne
	@PrimaryKeyJoinColumn
	private SourceforgeProjectEntity project;

	@Column(name = "name")
	private String name;

	@Column(name = "role")
	private String role;

	@Column(name = "unix_name")
	private String unixName;

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
