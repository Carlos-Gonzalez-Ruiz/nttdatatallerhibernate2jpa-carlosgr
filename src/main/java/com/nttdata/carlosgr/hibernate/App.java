package com.nttdata.carlosgr.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase principal de la aplicación.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class App {
	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	/**
	 * Función main de la clase.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		try (final Session session = getSessionFactory().openSession()) {

			InsertionDemo.setSession(session);
			InsertionDemo.setAuditCred(new AuditCredentials("SYS", new Date()));

			InsertionDemo.insertData();

			LOG.info("Se han insertado todos los datos.");

			InsertionDemo.showData();

		} catch (HibernateException e) {
			LOG.error("No se ha podido construir la sesión: {}", e.getMessage());
		}
	}

	/**
	 * Método privado que devuelve una SessionFactory.
	 * 
	 * @return SessionFactory
	 */
	private static SessionFactory getSessionFactory() throws HibernateException {
		return new Configuration().configure().buildSessionFactory();
	}

}
