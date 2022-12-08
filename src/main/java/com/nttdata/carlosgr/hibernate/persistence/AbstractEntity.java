package com.nttdata.carlosgr.hibernate.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase abstracta que define la entidad abstracta (AbstractEntity).
 * 
 * @author NTT Data - Carlos González Ruiz
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	/** Serial Version ID */
	private static final long serialVersionUID = 1L;
	/** Auditoria - Usuario que actualiza */
	private String updateUser;
	/** Auditoria - Fecha de actualización */
	private Date updateDate;

	/**
	 * @return the updateUser
	 */
	@Column(name = "AUDIT_UPDATED_USER", nullable = false)
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the updateDate
	 */
	@Column(name = "AUDIT_UPDATED_DATE", nullable = false)
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * Método abstracto para obtener ID de la tabla.
	 * 
	 * @return Long
	 */
	@Transient
	public abstract Long getId();

	/**
	 * Método abstracto para establecer ID de la tabla.
	 * 
	 * @param id
	 */
	public abstract void setId(final Long id);

	@Override
	public int hashCode() {
		return Objects.hash(updateDate, updateUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		return Objects.equals(updateDate, other.updateDate) && Objects.equals(updateUser, other.updateUser);
	}

}
