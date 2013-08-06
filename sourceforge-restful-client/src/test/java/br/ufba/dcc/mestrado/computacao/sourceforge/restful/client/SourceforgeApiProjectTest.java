package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import org.junit.Test;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProjectDTO;

public class SourceforgeApiProjectTest extends AbstractSourceforgeApiTest {

	
	@Test
	public void testSuccessGetProjectById() {
		SourceforgeProjectDTO response = getSourceforgeRestfulClient().getProjectById("293968");
		org.junit.Assert.assertNotNull(response);
	}
	
	@Test
	public void testSuccessGetProjectByName() {
		SourceforgeProjectDTO response = getSourceforgeRestfulClient().getProjectByName("tangoiconsprite");
		org.junit.Assert.assertNotNull(response);
	}
	
}
