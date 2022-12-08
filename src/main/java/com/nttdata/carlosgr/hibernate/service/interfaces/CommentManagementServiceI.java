package com.nttdata.carlosgr.hibernate.service.interfaces;

import java.util.List;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.persistence.Comment;
import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.User;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Interfaz para la gestión del servicio de comentario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public interface CommentManagementServiceI {
	/**
	 * Método para insertar un comentario.
	 * 
	 * @param comment
	 */
	public void insertComment(final Comment comment);

	/**
	 * Método para modifcar un comentario del registro.
	 * 
	 * @param comment
	 */
	public void updateComment(final Comment comment);

	/**
	 * Método para eliminar un comentario del registro.
	 * 
	 * @param comment
	 */
	public void deleteComment(final Comment comment);

	/**
	 * Método que devuelve el comentario con un ID específico.
	 * 
	 * @param id
	 * @return Comment
	 */
	public Comment searchById(final Long id);

	/**
	 * Método que devuelve todos los registros de la tabla de comentarios.
	 * 
	 * @return List<Comment>
	 */
	public List<Comment> searchAll();

	/**
	 * Método para escribir un comentario en una cuenta de usuario.
	 * 
	 * @param user
	 * @param content
	 * @param byUser
	 * @return Comment
	 */
	public Comment writeCommentUser(final User user, final String content, final User byUser,
			final AuditCredentials auditCred);

	/**
	 * Método para escribir un comentario en post.
	 * 
	 * @param post
	 * @param content
	 * @param byUser
	 * @param auditCred
	 * @return Comment
	 */
	public Comment writeCommentPost(final Post post, final String content, final User byUser,
			final AuditCredentials auditCred);

	/**
	 * Método para responder a un comentario.
	 * 
	 * @param replyingTo
	 * @param content
	 * @param byUser
	 * @param auditCred
	 * @return Comment
	 */
	public Comment replyComment(final Comment replyingTo, final String content, final User byUser,
			final AuditCredentials auditCred);

	/**
	 * Método para editar un comentario.
	 * 
	 * @param comment
	 * @param newContent
	 * @param auditCred
	 */
	public void editComent(final Comment comment, final String newContent, final AuditCredentials auditCred);
}
