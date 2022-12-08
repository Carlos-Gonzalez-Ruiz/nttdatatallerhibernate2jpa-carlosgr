package com.nttdata.carlosgr.hibernate.persistence.dao.interfaces;

import java.util.List;

import com.nttdata.carlosgr.hibernate.persistence.User;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Interfaz DAO de cuenta de usuario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public interface UserDaoI extends CommonDaoI<User> {

	/**
	 * Método que devuelve el usuario con un "nickname"
	 * 
	 * @param nickName
	 * @return User
	 */
	public User searchByNickName(final String nickName);

	/**
	 * Método que devuelve los usuarios cuyo nickname contenga "%patrón%", ordenados
	 * por lastLogged.
	 * 
	 * @param pattern
	 * @return List<User>
	 */
	public List<User> searchByNickNamePattern(final String pattern);

	/**
	 * Método que devuelve los usuarios cuyo nickname "%patrón%" y que tengan más de
	 * X número de posts publicados, ordenados por lastLogged.
	 * 
	 * @param pattern
	 * @return List<User>
	 */
	public List<User> searchByNickNamePatternAndPosts(final String pattern, final Long posts);

}
