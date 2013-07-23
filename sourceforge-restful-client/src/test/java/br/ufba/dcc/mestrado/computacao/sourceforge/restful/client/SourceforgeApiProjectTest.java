package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import org.junit.Test;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProject;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUser;

public class SourceforgeApiProjectTest extends AbstractSourceforgeApiTest {

	
	@Test
	public void testSuccessGetProjectById() {
		SourceforgeProject response = getSourceforgeRestfulClient().getProjectById("293968");
		org.junit.Assert.assertNotNull(response);
	}
	
	@Test
	public void testSuccessGetProjectByName() {
		SourceforgeProject response = getSourceforgeRestfulClient().getProjectByName("tangoiconsprite");
		org.junit.Assert.assertNotNull(response);
	}
	
}
