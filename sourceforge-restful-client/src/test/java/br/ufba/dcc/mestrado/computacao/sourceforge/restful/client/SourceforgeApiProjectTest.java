package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import org.junit.Test;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.project.SourceforgeProject;

public class SourceforgeApiProjectTest extends AbstractSourceforgeApiTest {

	
	@Test
	public void testSuccessGetProject() {
		SourceforgeProject response = getSourceforgeRestfulClient().getProjectById("156708");
		org.junit.Assert.assertNotNull(response);
	}
	
}
