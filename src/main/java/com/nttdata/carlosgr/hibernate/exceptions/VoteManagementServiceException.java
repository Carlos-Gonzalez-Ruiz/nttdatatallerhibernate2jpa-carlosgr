package com.nttdata.carlosgr.hibernate.exceptions;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase usada para lanzar excepciones al momento de usar UserManagementService
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class VoteManagementServiceException extends RuntimeException {

	/** Serial Version */
	private static final long serialVersionUID = 1L;

	/**
	 * Método constructor de la clase.
	 * 
	 * @param message
	 */
	public VoteManagementServiceException(String message) {
		super(message);
	}

}
