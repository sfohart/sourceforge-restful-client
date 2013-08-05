package br.ufba.dcc.mestrado.computacao.qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * Define m�todo ou vari�vel que deve ser injetada com valor lido de algum recurso arbitr�rio
 * como por exemplo  um arquivo <code>properties</code>
 * @author leandro.ferreira
 *
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface ConfigurationArrayValue {

	//chave a ser procurada
	@Nonbinding
	ConfigurationValue[] value() default {};
	
	//define se o valor deve ser definido
	boolean required() default true;
	
}
