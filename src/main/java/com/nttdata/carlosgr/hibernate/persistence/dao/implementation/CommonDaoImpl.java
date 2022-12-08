package com.nttdata.carlosgr.hibernate.persistence.dao.implementation;

import java.util.List;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.carlosgr.hibernate.persistence.AbstractEntity;
import com.nttdata.carlosgr.hibernate.persistence.dao.interfaces.CommonDaoI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Implementación de la interfaz DAO común.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public abstract class CommonDaoImpl<T extends AbstractEntity> implements CommonDaoI<T> {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(CommonDaoImpl.class);

	/** Clase de la entidad */
	private Class<T> entityClass;
	/** Sesión */
	private Session session;

	/**
	 * Constructor de la clase.
	 * 
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	protected CommonDaoImpl(Session session) {
		this.session = session;
		setEntityClass(
				(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	/**
	 * Método para abstraer la verificación de sesión.
	 */
	public void checkSession() {
		if (!session.getTransaction().isActive()) {

			LOG.debug("Comenzar transacción de la sesión.");
			session.getTransaction().begin();

		}
	}

	@Override
	public void insert(final T obj) {
		checkSession();

		// Guardar obj. (inserción)
		session.save(obj);
		session.flush();

		// Hacer commit.
		session.getTransaction().commit();
	}

	@Override
	public void update(final T obj) {
		checkSession();

		// Guardar o actualizar obj. (actualizar)
		session.saveOrUpdate(obj);

		// Hacer commit.
		session.getTransaction().commit();
	}

	@Override
	public void delete(final T obj) {
		checkSession();

		// Eliminar obj.
		session.delete(obj);

		// Hacer commit.
		session.getTransaction().commit();
	}

	@Override
	public T searchById(final Long id) {
		checkSession();

		// Devolver el registro con id.
		return session.get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchAll() {
		checkSession();

		// Devolver todos los registros de la tabla.
		return session.createQuery("FROM " + this.entityClass.getName()).list();
	}

	/**
	 * @return the entityClass
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

}
