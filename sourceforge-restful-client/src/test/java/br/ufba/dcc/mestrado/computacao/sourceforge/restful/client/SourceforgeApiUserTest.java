package br.ufba.dcc.mestrado.computacao.sourceforge.restful.client;

import org.junit.Test;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.user.SourceforgeUser;

public class SourceforgeApiUserTest extends AbstractSourceforgeApiTest {

	@Test
	public void testSuccessGetUserById() {
		SourceforgeUser response = getSourceforgeRestfulClient().getUserById("1414701");
		org.junit.Assert.assertNotNull(response);
	}
	
	@Test
	public void testSuccessGetUserByName() {
		SourceforgeUser response = getSourceforgeRestfulClient().getUserByName("groovecoder");
		org.junit.Assert.assertNotNull(response);
	}

}
