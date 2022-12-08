package com.nttdata.carlosgr.hibernate.persistence.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.PostDaoI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementación de la interfaz DAO de comentario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class PostDaoImpl extends CommonDaoImpl<Post> implements PostDaoI {

	/** Sesión de base de datos */
	private Session session;

	public PostDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public List<Post> searchByTitleDescription(String pattern) {
		checkSession();

		// Inizializar variables.
		final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		final CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

		// FROM
		final Root<Post> rootUser = criteriaQuery.from(Post.class);

		// WHERE
		final Predicate predicate1 = criteriaBuilder.like(rootUser.get("title"), pattern);
		final Predicate predicate2 = criteriaBuilder.like(rootUser.get("description"), pattern);

		// Crear consulta.
		criteriaQuery.select(rootUser).where(criteriaBuilder.and(predicate1, predicate2));

		// ORDER BY
		criteriaQuery.orderBy(criteriaBuilder.desc(rootUser.get("title")));

		return session.createQuery(criteriaQuery).getResultList();
	}

}
