package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import org.junit.Test;

public class SourceforgeApiActivityTest extends AbstractSourceforgeApiTest {

	
	@Test
	public void testSuccessGetActivityProjectByProjectId() {
		getSourceforgeRestfulClient().getProjectActivity("293968");		
	}
	
	
	
}
