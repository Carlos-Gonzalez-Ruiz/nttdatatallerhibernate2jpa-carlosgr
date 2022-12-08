package com.nttdata.carlosgr.hibernate.audit;

import java.util.Date;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase para facilitar el traspaso de credencialas a la hora de realizar con
 * las clases de "hibernate.service".
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class AuditCredentials {
	/** Nombre de usuario */
	private String userName;
	/** Fecha en la que se realiza la auditoria */
	private Date date;

	/**
	 * Método constructor de la clase.
	 * 
	 */
	public AuditCredentials(String userName, Date date) {
		super();
		this.userName = userName;
		this.date = date;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
