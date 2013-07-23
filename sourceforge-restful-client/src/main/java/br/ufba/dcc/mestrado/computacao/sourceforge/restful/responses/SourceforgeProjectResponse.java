package br.ufba.dcc.mestrado.computacao.sourceforge.restful.responses;

import java.util.List;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProject;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Project")
public class SourceforgeProjectResponse {

	@XStreamImplicit(itemFieldName = "Project")
	private List<SourceforgeProject> sourceforgeProjects;

	public List<SourceforgeProject> getSourceforgeProjects() {
		return sourceforgeProjects;
	}

	public void setSourceforgeProjects(
			List<SourceforgeProject> sourceforgeProjects) {
		this.sourceforgeProjects = sourceforgeProjects;
	}

}
