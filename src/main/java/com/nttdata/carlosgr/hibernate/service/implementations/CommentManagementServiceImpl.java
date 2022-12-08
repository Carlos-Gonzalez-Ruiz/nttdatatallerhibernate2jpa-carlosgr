package com.nttdata.carlosgr.hibernate.service.implementations;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.persistence.Comment;
import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.User;
import com.nttdata.carlosgr.hibernate.persistence.dao.implementation.CommentDaoImpl;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.CommentDaoI;
import com.nttdata.carlosgr.hibernate.service.interfaces.CommentManagementServiceI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementacioń de la interfaz para la gestión del servicio de comentario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class CommentManagementServiceImpl implements CommentManagementServiceI {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(CommentManagementServiceImpl.class);

	/** Objeto DAO de User */
	private CommentDaoI commentDao;

	/**
	 * Constructor de la clase.
	 * 
	 * @param session
	 */
	public CommentManagementServiceImpl(Session session) {
		super();
		this.commentDao = new CommentDaoImpl(session);
	}

	@Override
	public void insertComment(final Comment comment) {

		// Comprobar que comment sea un objeto "válido". (no nulo)
		if (comment != null) {
			commentDao.insert(comment);
		} else {
			LOG.error("No se pudo realizar la inserción.");
		}

	}

	@Override
	public void updateComment(final Comment comment) {

		// Comprobar que comment sea un objeto "válido". (no nulo y sin ID nula)
		if (comment != null && comment.getCommentId() != null) {
			commentDao.update(comment);
		} else {
			LOG.error("No se pudo realizar la actualización.");
		}

	}

	@Override
	public void deleteComment(final Comment comment) {

		// Comprobar que comment sea un objeto "válido". (no nulo y sin ID nula)
		if (comment != null && comment.getCommentId() != null) {
			commentDao.delete(comment);
		} else {
			LOG.error("No se pudo realizar la eliminación.");
		}

	}

	@Override
	public Comment searchById(final Long id) {
		return commentDao.searchById(id);
	}

	@Override
	public List<Comment> searchAll() {
		return commentDao.searchAll();
	}

	@Override
	public Comment writeCommentUser(final User user, final String content, final User byUser,
			final AuditCredentials auditCred) {
		// Crear objeto.
		Comment comment = new Comment();

		// Establecer atributos.
		comment.setUser(user);
		comment.setContent(content);
		comment.setByUser(byUser);

		comment.setFirstPublished(new Date());

		comment.setUpdateUser(auditCred.getUserName());
		comment.setUpdateDate(auditCred.getDate());

		// Insertar objeto.
		commentDao.insert(comment);

		return comment;
	}

	@Override
	public Comment writeCommentPost(final Post post, final String content, final User byUser,
			final AuditCredentials auditCred) {
		// Crear objeto.
		Comment comment = new Comment();

		// Establecer atributos.
		comment.setPost(post);
		comment.setContent(content);
		comment.setByUser(byUser);

		comment.setFirstPublished(new Date());

		comment.setUpdateUser(auditCred.getUserName());
		comment.setUpdateDate(auditCred.getDate());

		// Insertar objeto.
		commentDao.insert(comment);

		return comment;
	}

	@Override
	public Comment replyComment(final Comment replyingTo, final String content, final User byUser,
			final AuditCredentials auditCred) {
		// Crear objeto.
		Comment comment = new Comment();

		// Establecer atributos.
		comment.setReplyingTo(replyingTo);
		comment.setContent(content);
		comment.setByUser(byUser);

		comment.setFirstPublished(new Date());

		comment.setUpdateUser(auditCred.getUserName());
		comment.setUpdateDate(auditCred.getDate());

		// Insertar objeto.
		commentDao.insert(comment);

		return comment;
	}

	@Override
	public void editComent(final Comment comment, final String newContent, final AuditCredentials auditCred) {
		// Establecer atributos.
		comment.setContent(newContent);

		comment.setLastEdited(new Date());

		comment.setUpdateUser(auditCred.getUserName());
		comment.setUpdateDate(auditCred.getDate());

		// Actualizar objeto.
		commentDao.update(comment);
	}

}
