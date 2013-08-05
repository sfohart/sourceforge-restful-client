package br.ufba.dcc.mestrado.computacao.producer;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author leandro.ferreira
 *
 *
 * @see https://www.42lines.net/2011/11/21/adding-jpahibernate-into-the-cdi-and-wicket-mix/
 */
public class EntityManagerFactoryProducer {
	
	private static final String PERSISTENCE_UNIT = "ohloh";
	
	@Produces
	@ApplicationScoped
	public EntityManagerFactory create() {
		
		Map<String, Object> configOverrides = new HashMap<String, Object>();
		configOverrides.put("hibernate.hbm2ddl.auto", "update");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, configOverrides);
		return entityManagerFactory;
	}
	
	public void destroy(@Disposes EntityManagerFactory factory) {
		factory.close();
	}

}
