package com.nttdata.carlosgr.hibernate.service.implementations;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.exceptions.VoteManagementServiceException;
import com.nttdata.carlosgr.hibernate.persistence.Comment;
import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.User;
import com.nttdata.carlosgr.hibernate.persistence.Vote;
import com.nttdata.carlosgr.hibernate.persistence.Vote.VoteType;
import com.nttdata.carlosgr.hibernate.persistence.dao.implementation.VoteDaoImpl;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.VoteDaoI;
import com.nttdata.carlosgr.hibernate.service.interfaces.VoteManagementServiceI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementacioń de la interfaz para la gestión del servicio de usuario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class VoteManagementServiceImpl implements VoteManagementServiceI {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(VoteManagementServiceImpl.class);

	/** Objeto DAO de Vote */
	private VoteDaoI voteDao;

	/**
	 * Constructor de la clase.
	 * 
	 * @param session
	 */
	public VoteManagementServiceImpl(Session session) {
		super();
		this.voteDao = new VoteDaoImpl(session);
	}

	@Override
	public void insertVote(final Vote vote) {

		// Comprobar que user sea un objeto "válido". (no nulo)
		if (vote != null) {
			voteDao.insert(vote);
		} else {
			LOG.error("No se pudo realizar la inserción.");
		}

	}

	@Override
	public void updateVote(final Vote vote) {

		// Comprobar que user sea un objeto "válido". (no nulo y sin ID nula)
		if (vote != null && vote.getVoteId() != null) {
			voteDao.update(vote);
		} else {
			LOG.error("No se pudo realizar la actualización.");
		}

	}

	@Override
	public void deleteVote(final Vote vote) {

		// Comprobar que user sea un objeto "válido". (no nulo y sin ID nula)
		if (vote != null && vote.getVoteId() != null) {
			voteDao.delete(vote);
		} else {
			LOG.error("No se pudo realizar la eliminación.");
		}

	}

	@Override
	public Vote searchById(final Long id) {
		return voteDao.searchById(id);
	}

	@Override
	public List<Vote> searchAll() {
		return voteDao.searchAll();
	}

	@Override
	public Vote voteUser(final User user, final VoteType type, final User byUser, final AuditCredentials auditCred)
			throws VoteManagementServiceException {

		// Comprobar que no se dé a si mismo un voto.
		if (user.equals(byUser)) {
			throw new VoteManagementServiceException("No se permite darse voto a sí mismo.");
		}

		// Crear objeto.
		Vote vote = new Vote();

		// Establecer atributos.
		vote.setUser(user);
		vote.setType(type);
		vote.setByUser(byUser);

		vote.setUpdateUser(auditCred.getUserName());
		vote.setUpdateDate(auditCred.getDate());

		// Insertar objeto.
		voteDao.insert(vote);

		return vote;
	}

	@Override
	public Vote votePost(final Post post, final VoteType type, final User byUser, final AuditCredentials auditCred)
			throws VoteManagementServiceException {

		// Comprobar que no se dé a si mismo un voto.
		if (post.getUser() != null && post.getUser().equals(byUser)) {
			throw new VoteManagementServiceException("No se permite darse voto su propio contenido.");
		}

		// Crear objeto.
		Vote vote = new Vote();

		// Establecer atributos.
		vote.setPost(post);
		vote.setType(type);
		vote.setByUser(byUser);

		vote.setUpdateUser(auditCred.getUserName());
		vote.setUpdateDate(auditCred.getDate());

		// Insertar objeto.
		voteDao.insert(vote);

		return vote;
	}

	@Override
	public Vote voteComment(final Comment comment, final VoteType type, final User byUser,
			final AuditCredentials auditCred) throws VoteManagementServiceException {

		// Comprobar que no se dé a si mismo un voto.
		if (comment.getUser() != null && comment.getUser().equals(byUser)) {
			throw new VoteManagementServiceException("No se permite darse voto su propio contenido.");
		}

		// Crear objeto.
		Vote vote = new Vote();

		// Establecer atributos.
		vote.setComment(comment);
		vote.setType(type);
		vote.setByUser(byUser);

		vote.setUpdateUser(auditCred.getUserName());
		vote.setUpdateDate(auditCred.getDate());

		// Insertar objeto.
		voteDao.insert(vote);

		return vote;
	}

}
