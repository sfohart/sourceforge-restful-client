package br.ufba.dcc.mestrado.computacao.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * 
 * @author leandro.ferreira
 *
 * @see https://www.42lines.net/2011/11/21/adding-jpahibernate-into-the-cdi-and-wicket-mix/
 */
public class EntityManagerProducer {

	@Inject
	private EntityManagerFactory entityManagerFactory;
	
	@Produces
	@ApplicationScoped
	public EntityManager create() {
		return entityManagerFactory.createEntityManager();
	}
	
	public void destroy(@Disposes EntityManager entityManager) {
		entityManager.close();
	}
	
}
