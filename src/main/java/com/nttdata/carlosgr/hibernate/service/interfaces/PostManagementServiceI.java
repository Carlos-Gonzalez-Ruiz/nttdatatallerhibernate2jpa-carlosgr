package com.nttdata.carlosgr.hibernate.service.interfaces;

import java.util.List;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.User;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Interfaz para la gestión del servicio de publicación / "post".
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public interface PostManagementServiceI {
	/**
	 * Método para insertar un post.
	 * 
	 * @param post
	 */
	public void insertPost(final Post post);

	/**
	 * Método para modifcar un post del registro.
	 * 
	 * @param post
	 */
	public void updatePost(final Post post);

	/**
	 * Método para eliminar un post del registro.
	 * 
	 * @param post
	 */
	public void deletePost(final Post post);

	/**
	 * Método que devuelve el post con un ID específico.
	 * 
	 * @param id
	 * @return Post
	 */
	public Post searchById(final Long id);

	/**
	 * Método que devuelve los post que contengan "%patrón%" en el título o en la
	 * descripción, ordenado alfabéticamente.
	 * 
	 * @param pattern
	 * @return List<Post>
	 */
	public List<Post> searchByTitleDescription(String pattern);

	/**
	 * Método que devuelve todos los registros de la tabla de posts.
	 * 
	 * @return List<Post>
	 */
	public List<Post> searchAll();

	/**
	 * Método para realizar la subida de un post por un usuario.
	 * 
	 * @param title
	 * @param urlVideo
	 * @param description
	 * @param user
	 * @param auditCred
	 * @return Post
	 */
	public Post uploadPost(final String title, final String urlVideo, final String description, final User user,
			final AuditCredentials auditCred);

	/**
	 * Método para actualizar el título y la descripción de un post.
	 * 
	 * @param post
	 * @param newTitle
	 * @param newDescription
	 * @param auditCred
	 */
	public void editPost(final Post post, final String newTitle, final String newDescription,
			final AuditCredentials auditCred);
}
