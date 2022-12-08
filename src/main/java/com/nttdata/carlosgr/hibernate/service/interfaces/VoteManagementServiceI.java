package com.nttdata.carlosgr.hibernate.service.interfaces;

import java.util.List;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.exceptions.VoteManagementServiceException;
import com.nttdata.carlosgr.hibernate.persistence.Comment;
import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.User;
import com.nttdata.carlosgr.hibernate.persistence.Vote;
import com.nttdata.carlosgr.hibernate.persistence.Vote.VoteType;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Interfaz para la gestión del servicio de voto.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public interface VoteManagementServiceI {
	/**
	 * Método para insertar un voto.
	 * 
	 * @param vote
	 */
	public void insertVote(final Vote vote);

	/**
	 * Método para modifcar un voto del registro.
	 * 
	 * @param vote
	 */
	public void updateVote(final Vote vote);

	/**
	 * Método para eliminar un voto del registro.
	 * 
	 * @param vote
	 */
	public void deleteVote(final Vote vote);

	/**
	 * Método que devuelve el voto con un ID específico.
	 * 
	 * @param id
	 * @return User
	 */
	public Vote searchById(final Long id);

	/**
	 * Método que devuelve todos los registros de la tabla de votos.
	 * 
	 * @return List<Vote>
	 */
	public List<Vote> searchAll();

	/**
	 * Método que devuelve el voto que ha sido dado a cierto usuario. Lanza una
	 * excepción en caso de no poder realizar el voto.
	 * 
	 * @param user
	 * @param type
	 * @param byUser
	 * @param auditCred
	 * @return Vote
	 */
	public Vote voteUser(final User user, final VoteType type, final User byUser, final AuditCredentials auditCred)
			throws VoteManagementServiceException;

	/**
	 * Método que devuelve el voto que ha sido dado a cierto post. Lanza una
	 * excepción en caso de no poder realizar el voto.
	 * 
	 * @param post
	 * @param type
	 * @param byUser
	 * @param auditCred
	 * @return Vote
	 */
	public Vote votePost(final Post post, final VoteType type, final User byUser, final AuditCredentials auditCred)
			throws VoteManagementServiceException;

	/**
	 * Método que devuelve el voto que ha sido dado a cierto comentario. Lanza una
	 * excepción nulo en caso de no poder realizar el voto.
	 * 
	 * @param comment
	 * @param type
	 * @param byUser
	 * @param auditCred
	 * @return Vote
	 */
	public Vote voteComment(final Comment comment, final VoteType type, final User byUser,
			final AuditCredentials auditCred) throws VoteManagementServiceException;
}
