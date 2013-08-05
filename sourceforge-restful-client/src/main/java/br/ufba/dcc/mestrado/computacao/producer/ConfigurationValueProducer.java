package br.ufba.dcc.mestrado.computacao.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import br.ufba.dcc.mestrado.computacao.qualifier.ConfigurationArrayValue;
import br.ufba.dcc.mestrado.computacao.qualifier.ConfigurationValue;
import br.ufba.dcc.mestrado.computacao.resolver.PropertyResolver;

/**
 *  
 * Produz campos anotados com {@link ConfigurationValue}. Responsável por dar suporte a conversão entre
 * tipos (principalmente de String para qualquer outro tipo requerido pelo usuário)
 * 
 * Esses produtores não deve estar interessado em que os campos são lidos. É o {@link PropertyResolver}
 * quem é responsável pela carga da configuração
 *  
 * @author leandro.ferreira
 *
 */
public class ConfigurationValueProducer {
	
	@Inject
	private PropertyResolver propertyResolver;
	
	/**
	 * Método produtor princial. Tenta encontrar um valor de propriedade utilizando as seguintes chaves:
	 * 
	 * <ol>
	 * 		<li>Propriedade <code>key</code> da anotação {@link ConfigurationValue} (se não encontrou 
	 * 		chave alguma, mas o campo está definido como obrigatório, retorna null</li>
	 * 
	 * 		<li>Nomes de campos de classe totalmente qualificados, por exemplo, 
	 * 		<code>eu.awaketech.MyBean.myField</code>. Se o valor for nulo, tente a última cartada</li>
	 * 
	 * 		<li>Nome do campo de classe, por exemplo, <code>myField</code> do exemplo acima. Se o valor
	 * 		for nulo, não há o que se fazer, retorne null.</li>
	 * </ol>
	 * 
	 * @param ip
	 * @return
	 */
	@Produces
    @ConfigurationValue
    public String getStringConfigValue(InjectionPoint ip) {

        // Trying with explicit key defined on the field
        String key = ip.getAnnotated().getAnnotation(ConfigurationValue.class).value();
        boolean isKeyDefined = !key.trim().isEmpty();
        boolean valueRequired = ip.getAnnotated().getAnnotation(ConfigurationValue.class).required();
        
        String value = processStringValue(ip, key, isKeyDefined, valueRequired);

        return value;
    }
	
	private String processStringValue(
			InjectionPoint ip, 
			String key, 
			boolean isKeyDefined, 
			boolean valueRequired) {
		
		String fqn = ip.getMember().getDeclaringClass().getName() + "." + ip.getMember().getName();
		
		if (isKeyDefined) {
            return propertyResolver.getValue(key);
        }

        // Falling back to fully-qualified field name resolving.
        key = fqn;
        String value = propertyResolver.getValue(fqn);

        // No luck... so perhaps just the field name?
        if (value == null) {
            key = ip.getMember().getName();
            value = propertyResolver.getValue(key);
        }

        // No can do - no value found but you've said it's required.
        if (value == null && valueRequired) {
            throw new IllegalStateException("Nenhum valor definido para o campo: " + fqn + " mas o campo foi marcado como obrigatório.");
        }

        return value;
	}
	
	@Produces
    @ConfigurationArrayValue
	public String[] getStringConfigArrayValue(InjectionPoint ip) {
		String[] value = null;
		
		
		ConfigurationValue[] keys = ip.getAnnotated().getAnnotation(ConfigurationArrayValue.class).value();
		
		if (keys != null && keys.length > 0) {
			value = new String[keys.length];
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i].value();
		        boolean isKeyDefined = !key.trim().isEmpty();
		        boolean valueRequired =  keys[i].required();
		        
		        value[i] = processStringValue(ip, key, isKeyDefined, valueRequired);
			}
		}
		
		return value;
	}
	
	@Produces
    @ConfigurationValue
    public Double getDoubleConfigValue(InjectionPoint ip) {
        String value = getStringConfigValue(ip);

        return (value != null) ? Double.valueOf(value) : null;
    }
	
	@Produces
    @ConfigurationValue
    public Integer getIntegerConfigValue(InjectionPoint ip) {
        String value = getStringConfigValue(ip);

        return (value != null) ? Integer.valueOf(value) : null;
    }
	
	@Produces
    @ConfigurationValue
    public Long getLongConfigValue(InjectionPoint ip) {
        String value = getStringConfigValue(ip);

        return (value != null) ? Long.valueOf(value) : null;
    }
	
	@Produces
	@ConfigurationValue
	public Boolean getBooleanConfigValue(InjectionPoint ip) {
		String value = getStringConfigValue(ip);
		
		
		return (value != null) ? Boolean.valueOf(value) : null;
	}
	

}
