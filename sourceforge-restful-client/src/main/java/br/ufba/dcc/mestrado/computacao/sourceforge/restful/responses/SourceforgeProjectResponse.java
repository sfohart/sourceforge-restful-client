package br.ufba.dcc.mestrado.computacao.sourceforge.restful.responses;

import java.util.List;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProjectDTO;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Project")
public class SourceforgeProjectResponse {

	@XStreamImplicit(itemFieldName = "Project")
	private List<SourceforgeProjectDTO> sourceforgeProjects;

	public List<SourceforgeProjectDTO> getSourceforgeProjects() {
		return sourceforgeProjects;
	}

	public void setSourceforgeProjects(
			List<SourceforgeProjectDTO> sourceforgeProjects) {
		this.sourceforgeProjects = sourceforgeProjects;
	}

}
