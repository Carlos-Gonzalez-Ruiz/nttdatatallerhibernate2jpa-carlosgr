package com.nttdata.carlosgr.hibernate.persistence.dao.implementation;

import org.hibernate.Session;

import com.nttdata.carlosgr.hibernate.persistence.Comment;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.CommentDaoI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementación de la interfaz DAO de comentario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class CommentDaoImpl extends CommonDaoImpl<Comment> implements CommentDaoI {

	/** Sesión de base de datos */
	@SuppressWarnings("unused")
	private Session session;

	public CommentDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

}
