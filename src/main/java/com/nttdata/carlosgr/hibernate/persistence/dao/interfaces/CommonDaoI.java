package com.nttdata.carlosgr.hibernate.persistence.dao.interfaces;

import java.util.List;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Interfaz del DAO común.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public interface CommonDaoI<T> {

	/**
	 * Método que elimina inserta un nuevo registro.
	 * 
	 * @param obj
	 */
	public void insert(final T obj);

	/**
	 * Método que modifica / actualiza un registro específico.
	 * 
	 * @param obj
	 */
	public void update(final T obj);

	/**
	 * Método que elimina un registro específico.
	 * 
	 * @param obj
	 */
	public void delete(final T obj);

	/**
	 * Método que devuelve todos los registros según un ID.
	 * 
	 * @param id
	 */
	public T searchById(final Long id);

	/**
	 * Método que devuelve todos los registros.
	 * 
	 * return List<T>
	 */
	public List<T> searchAll();

}
