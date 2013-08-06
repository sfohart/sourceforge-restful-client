package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeSVNRepositoryDTO.NODE_NAME)
public class SourceforgeSVNRepositoryDTO implements SourceforgeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8085245842316762509L;

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
