package com.nttdata.carlosgr.hibernate.service.implementations;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.exceptions.UserManagementServiceException;
import com.nttdata.carlosgr.hibernate.persistence.User;
import com.nttdata.carlosgr.hibernate.persistence.dao.implementation.UserDaoImpl;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.UserDaoI;
import com.nttdata.carlosgr.hibernate.security.Hash;
import com.nttdata.carlosgr.hibernate.service.interfaces.UserManagementServiceI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementacioń de la interfaz para la gestión del servicio de usuario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class UserManagementServiceImpl implements UserManagementServiceI {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(UserManagementServiceImpl.class);

	/** Objeto DAO de User */
	private UserDaoI userDao;

	/**
	 * Constructor de la clase.
	 * 
	 * @param session
	 */
	public UserManagementServiceImpl(Session session) {
		super();
		this.userDao = new UserDaoImpl(session);
	}

	@Override
	public void insertUser(final User user) {

		// Comprobar que user sea un objeto "válido". (no nulo)
		if (user != null) {
			userDao.insert(user);
		} else {
			LOG.error("No se pudo realizar la inserción.");
		}

	}

	@Override
	public void updateUser(final User user) {

		// Comprobar que user sea un objeto "válido". (no nulo y sin ID nula)
		if (user != null && user.getUserId() != null) {
			userDao.update(user);
		} else {
			LOG.error("No se pudo realizar la actualización.");
		}

	}

	@Override
	public void deleteUser(final User user) {

		// Comprobar que user sea un objeto "válido". (no nulo y sin ID nula)
		if (user != null && user.getUserId() != null) {
			userDao.delete(user);
		} else {
			LOG.error("No se pudo realizar la eliminación.");
		}

	}

	@Override
	public User searchById(final Long id) {
		return userDao.searchById(id);
	}

	@Override
	public User searchByNickName(final String nickName) {
		return userDao.searchByNickName(nickName);
	}

	@Override
	public List<User> searchByNickNamePattern(final String pattern) {
		return userDao.searchByNickNamePattern(pattern);
	}

	@Override
	public List<User> searchByNickNamePatternAndPosts(String pattern, Long posts) {
		return userDao.searchByNickNamePatternAndPosts(pattern, posts);
	}

	@Override
	public List<User> searchAll() {
		return userDao.searchAll();
	}

	@Override
	public User registerUser(final String nickname, final String password, final AuditCredentials auditCred) {
		// Crear objeto para trabajar con hashes.
		Hash hash = new Hash();

		// Crear objeto.
		User user = new User();

		// Establecer atributos.
		user.setNickName(nickname);
		user.setPasswordHash(hash.hashString(password));
		user.setDescription("");

		user.setFirstLogged(new Date());

		user.setUpdateUser(auditCred.getUserName());
		user.setUpdateDate(auditCred.getDate());

		// Insertar objeto.
		userDao.insert(user);

		return user;
	}

	@Override
	public boolean loginUser(final String nickName, final String password, final AuditCredentials auditCred) {
		boolean out = false;

		// Crear objeto para trabajar con hashes.
		Hash hash = new Hash();

		// Crear objeto.
		User user = userDao.searchByNickName(nickName);

		if (hash.checkHashString(user.getPasswordHash(), password)) {

			user.setLastLogged(new Date());

			user.setUpdateUser(auditCred.getUserName());
			user.setUpdateDate(auditCred.getDate());

			// Actualizar objeto.
			userDao.update(user);

			out = true;

		}

		return out;
	}

	@Override
	public void followUser(final User followUser, final User user, final AuditCredentials auditCred)
			throws UserManagementServiceException {
		// Comprobar que no sean el mismo usuario.
		if (followUser.equals(user)) {
			throw new UserManagementServiceException("No se permite seguirse a si mismo.");
		}

		// Comprobar que no se vuelva a seguir 2 veces al mismo usuario.
		// Como ya es un Set, no haría falta comprobarlo, pero sería recomendable
		// hacerlo.
		if (followUser.getFollowers() != null && followUser.getFollowers().contains(user)) {
			throw new UserManagementServiceException("No se permite seguir 2 veces al mismo usuario.");
		}

		// Añadir usuario a los seguidos
		if (user.getFollowing() == null) {
			user.setFollowing(new HashSet<>());
		}

		user.getFollowing().add(followUser);

		user.setUpdateUser(auditCred.getUserName());
		user.setUpdateDate(auditCred.getDate());

		// Actualizar objeto.
		userDao.update(user);
	}

	@Override
	public void unfollowUser(final User unfollowUser, final User user, final AuditCredentials auditCred) {
		// Añadir usuario a los seguidos
		if (user.getFollowing() != null) {
			user.getFollowing().remove(unfollowUser);
		}

		user.setUpdateUser(auditCred.getUserName());
		user.setUpdateDate(auditCred.getDate());

		// Actualizar objeto.
		userDao.update(user);
	}

}
