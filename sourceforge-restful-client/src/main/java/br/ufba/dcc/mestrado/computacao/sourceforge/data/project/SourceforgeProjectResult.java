package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class SourceforgeProjectResult {

	@XStreamImplicit(itemFieldName="Project")
	private List<SourceforgeProject> sourceforgeProjects;

	public List<SourceforgeProject> getSourceforgeProjects() {
		return sourceforgeProjects;
	}

	public void setSourceforgeProjects(List<SourceforgeProject> sourceforgeProjects) {
		this.sourceforgeProjects = sourceforgeProjects;
	}
	
	
	
	
}
