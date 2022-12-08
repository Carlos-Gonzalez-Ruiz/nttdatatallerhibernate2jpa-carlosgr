package com.nttdata.carlosgr.hibernate.service.implementations;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.User;
import com.nttdata.carlosgr.hibernate.persistence.dao.implementation.PostDaoImpl;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.PostDaoI;
import com.nttdata.carlosgr.hibernate.service.interfaces.PostManagementServiceI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementacioń de la interfaz para la gestión del servicio de publicación /
 * "post".
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class PostManagementServiceImpl implements PostManagementServiceI {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(PostManagementServiceImpl.class);

	/** Objeto DAO de User */
	private PostDaoI postDao;

	/**
	 * Constructor de la clase.
	 * 
	 * @param session
	 */
	public PostManagementServiceImpl(Session session) {
		super();
		this.postDao = new PostDaoImpl(session);
	}

	@Override
	public void insertPost(final Post post) {

		// Comprobar que post sea un objeto "válido". (no nulo)
		if (post != null) {
			postDao.insert(post);
		} else {
			LOG.error("No se pudo realizar la inserción.");
		}

	}

	@Override
	public void updatePost(final Post post) {

		// Comprobar que post sea un objeto "válido". (no nulo y sin ID nula)
		if (post != null && post.getPostId() != null) {
			postDao.update(post);
		} else {
			LOG.error("No se pudo realizar la actualización.");
		}

	}

	@Override
	public void deletePost(final Post post) {

		// Comprobar que post sea un objeto "válido". (no nulo y sin ID nula)
		if (post != null && post.getPostId() != null) {
			postDao.delete(post);
		} else {
			LOG.error("No se pudo realizar la eliminación.");
		}

	}

	@Override
	public Post searchById(final Long id) {
		return postDao.searchById(id);
	}

	@Override
	public List<Post> searchByTitleDescription(String pattern) {
		return postDao.searchByTitleDescription(pattern);
	}

	@Override
	public List<Post> searchAll() {
		return postDao.searchAll();
	}

	@Override
	public Post uploadPost(final String title, final String urlVideo, final String description, final User user,
			final AuditCredentials auditCred) {
		// Crear objeto.
		Post post = new Post();

		// Establecer atributos.
		post.setTitle(title);
		post.setUrlVideo(urlVideo);
		post.setDescription(description);
		post.setUser(user);

		post.setFirstPublished(new Date());

		post.setUpdateUser(auditCred.getUserName());
		post.setUpdateDate(auditCred.getDate());

		// Insertar objeto.
		postDao.insert(post);

		return post;
	}

	@Override
	public void editPost(final Post post, final String newTitle, final String newDescription,
			final AuditCredentials auditCred) {
		// Establecer atributos.
		post.setTitle(newTitle);
		post.setDescription(newDescription);

		post.setLastEdited(new Date());

		post.setUpdateUser(auditCred.getUserName());
		post.setUpdateDate(auditCred.getDate());

		// Actualizar objeto.
		postDao.update(post);
	}

}
