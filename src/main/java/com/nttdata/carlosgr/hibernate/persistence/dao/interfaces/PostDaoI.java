package com.nttdata.carlosgr.hibernate.persistence.dao.interfaces;

import java.util.List;

import com.nttdata.carlosgr.hibernate.persistence.Post;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Interfaz DAO de comentario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public interface PostDaoI extends CommonDaoI<Post> {

	/**
	 * Método que devuelve los post que contengan "%patrón%" en el título o en la
	 * descripción, ordenado alfabéticamente por el título.
	 * 
	 * @param pattern
	 * @return List<Post>
	 */
	public List<Post> searchByTitleDescription(final String pattern);

}
