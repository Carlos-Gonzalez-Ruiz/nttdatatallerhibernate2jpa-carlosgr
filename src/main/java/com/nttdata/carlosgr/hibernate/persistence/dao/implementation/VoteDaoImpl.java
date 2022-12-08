package com.nttdata.carlosgr.hibernate.persistence.dao.implementation;

import org.hibernate.Session;

import com.nttdata.carlosgr.hibernate.persistence.Vote;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.VoteDaoI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementación de la interfaz DAO de voto.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class VoteDaoImpl extends CommonDaoImpl<Vote> implements VoteDaoI {

	/** Sesión de base de datos */
	@SuppressWarnings("unused")
	private Session session;

	public VoteDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

}
