package br.ufba.dcc.mestrado.computacao.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;

import br.ufba.dcc.mestrado.computacao.service.BaseSourceforgeService;
import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;
import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

public abstract class BaseSourceforgeServiceImpl<DTO extends SourceforgeDTO, E extends SourceforgeBaseEntity>		
		implements BaseSourceforgeService<DTO, E> {

	private Class<DTO> dtoClass;
	private Class<E> entityClass;

	@Override
	public E store(DTO dto) throws Exception {
		E entity = buildEntity(dto);
		
		validateEntity(entity);
		
		return entity;
	}
	
	public BaseSourceforgeServiceImpl(Class<DTO> dtoClass, Class<E> entityClass) {
		this.dtoClass = dtoClass;
		this.entityClass = entityClass;
	}

	@SuppressWarnings("rawtypes")
	protected Object deepCopySingleValue(Object origValue, Class<?> originClass, Class<?> destClass) throws Exception {
		if (origValue == null) {
			return null;
		}
		
		Object destValue = destClass.newInstance();		
		BeanUtils.copyProperties(destValue, origValue);
		
		for (Field field : originClass.getDeclaredFields()) {
			String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			
			if (Collection.class.isAssignableFrom(field.getType())) {
				Method getOrigMethod = originClass.getDeclaredMethod(getMethodName);
				
				if (getOrigMethod.invoke(origValue) != null) {
					Collection destCollection = deepCopyCollectionValue(
							origValue, 
							field,
							destValue,
							destClass.getDeclaredField(field.getName()),
							getOrigMethod);
				
					Method setDestMethod = destClass.getDeclaredMethod(
							setMethodName,
							destClass.getDeclaredField(field.getName()).getGenericType().getClass());
					setDestMethod.invoke(destValue, field.getType().cast(destCollection));
				}
			} else if (SourceforgeDTO.class.isAssignableFrom(field.getType())) {			
				Method getMethod = originClass.getDeclaredMethod(getMethodName);
					
				if (getMethod.invoke(origValue) != null) {
					Object destFieldObject = deepCopySingleValue(
							getMethod.invoke(origValue),  
							field.getType(), 
							destClass.getDeclaredField(field.getName()).getClass());
					
					destClass.getDeclaredField(field.getName()).set(destValue, destFieldObject);					
				}
				
			}
		}
		
		return destValue;
	}
	
	protected void validateEntity(E entity) throws Exception {
		
	}
	
	@SuppressWarnings("rawtypes")
	protected E buildEntity(DTO dto) throws Exception {
		E entity = entityClass.newInstance();

		BeanUtils.copyProperties(entity, dto);

		for (Field field : dtoClass.getDeclaredFields()) {
			String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			
			if (Collection.class.isAssignableFrom(field.getType())) {
				Method getOrigMethod = dtoClass.getDeclaredMethod(getMethodName);
				
				if (getOrigMethod.invoke(dto) != null) {
					Collection destCollection = deepCopyCollectionValue(
							dto, 
							field,
							entity,
							entityClass.getDeclaredField(field.getName()),
							getOrigMethod);
				
					Method setDestMethod = entityClass.getDeclaredMethod(
							setMethodName, 
							entityClass.getDeclaredField(field.getName()).getType()
							);
					setDestMethod.invoke(entity, field.getType().cast(destCollection));
				}
			} else if (SourceforgeDTO.class.isAssignableFrom(field.getType())) {
				Method getMethod = dtoClass.getDeclaredMethod(getMethodName);
				if (getMethod.invoke(dto) != null) {
					Object destFieldObject = deepCopySingleValue(
							getMethod.invoke(dto), 
							field.getType(), 
							entityClass.getDeclaredField(field.getName()).getClass());
					
					entityClass.getDeclaredField(field.getName()).set(entity, destFieldObject);
				}
			}
		}

		return entity;
	}

	/**
	 * @param origValue
	 * @param origField
	 * @param getOrigMethod
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection deepCopyCollectionValue(Object origValue, Field origField, Object destValue, Field destField, Method getOrigMethod) throws Exception{
		
		ParameterizedType itemOrigParameterizedType = (ParameterizedType) origField.getGenericType();
		ParameterizedType itemDestParameterizedType = (ParameterizedType) destField.getGenericType();
		
		Class<?> itemOrigClass = (Class<?>) itemOrigParameterizedType.getActualTypeArguments()[0];
		Class<?> itemDestClass = (Class<?>) itemDestParameterizedType.getActualTypeArguments()[0];
		
		Collection origCollection = (Collection) getOrigMethod.invoke(origValue);
		Collection destCollection = new ArrayList();
		
		for (Object itemOrigValue : origCollection) {
			Object itemDestValue = deepCopySingleValue(itemOrigValue, itemOrigClass, itemDestClass);
			destCollection.add(itemDestValue);
		}
		
		return destCollection;
	}

}
