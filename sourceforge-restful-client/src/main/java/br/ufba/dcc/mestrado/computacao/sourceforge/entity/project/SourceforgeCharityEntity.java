package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeCharityEntity.NODE_NAME)
public class SourceforgeCharityEntity extends SourceforgeBaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2934980662744315307L;

	public final static String NODE_NAME = "charity";

	@Column(name = "allocation_percent")
	private String allocationPercent;
	
	@Column(name = "organization")
	private String organization;

	public String getAllocationPercent() {
		return allocationPercent;
	}

	public void setAllocationPercent(String allocationPercent) {
		this.allocationPercent = allocationPercent;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
