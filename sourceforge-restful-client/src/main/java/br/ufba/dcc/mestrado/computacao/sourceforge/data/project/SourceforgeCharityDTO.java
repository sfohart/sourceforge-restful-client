package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeCharityDTO.NODE_NAME)
public class SourceforgeCharityDTO implements SourceforgeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -379048219230813372L;

	public final static String NODE_NAME = "charity";

	@XStreamAlias("allocationpercent")
	private String allocationPercent;
	
	@XStreamAlias("organization")
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
