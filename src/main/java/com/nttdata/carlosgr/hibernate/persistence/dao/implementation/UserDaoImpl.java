package com.nttdata.carlosgr.hibernate.persistence.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.User;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.UserDaoI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementación de la interfaz DAO de cuenta de usuario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDaoI {

	/** Sesión de base de datos */
	private Session session;

	public UserDaoImpl(final Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public User searchByNickName(final String nickName) {
		checkSession();

		return (User) session.createQuery("FROM User WHERE nickName='" + nickName + "'").getSingleResult();
	}

	@Override
	public List<User> searchByNickNamePattern(final String pattern) {
		checkSession();

		// Inizializar variables.
		final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

		// FROM
		final Root<User> rootUser = criteriaQuery.from(User.class);

		// WHERE
		final Predicate predicate = criteriaBuilder.like(rootUser.get("nickName"), pattern);

		// Crear consulta.
		criteriaQuery.select(rootUser).where(predicate);

		// ORDER BY
		criteriaQuery.orderBy(criteriaBuilder.desc(rootUser.get("lastLogged")));

		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<User> searchByNickNamePatternAndPosts(String pattern, Long posts) {
		checkSession();

		// Inizializar variables.
		final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

		// FROM
		final Root<User> rootUser = criteriaQuery.from(User.class);

		// JOIN
		final Join<User, Post> joinUserPost = rootUser.join("posts");

		// WHERE
		final Predicate predicate1 = criteriaBuilder.like(rootUser.get("nickName"), pattern);
		final Predicate predicate2 = criteriaBuilder.greaterThan(criteriaBuilder.count(joinUserPost), posts);

		// Crear consulta.
		criteriaQuery.select(rootUser).where(predicate1).having(predicate2);

		// ORDER BY
		criteriaQuery.orderBy(criteriaBuilder.desc(rootUser.get("lastLogged")));

		return session.createQuery(criteriaQuery).getResultList();
	}

}
