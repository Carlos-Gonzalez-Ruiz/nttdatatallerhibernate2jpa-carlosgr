package com.nttdata.carlosgr.hibernate.service.interfaces;

import java.util.List;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.exceptions.UserManagementServiceException;
import com.nttdata.carlosgr.hibernate.persistence.User;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Interfaz para la gestión del servicio de usuario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public interface UserManagementServiceI {
	/**
	 * Método para insertar un usuario.
	 * 
	 * @param user
	 */
	public void insertUser(final User user);

	/**
	 * Método para modifcar un usuario del registro.
	 * 
	 * @param user
	 */
	public void updateUser(final User user);

	/**
	 * Método para eliminar un usuario del registro.
	 * 
	 * @param user
	 */
	public void deleteUser(final User user);

	/**
	 * Método que devuelve el usuario con un ID específico.
	 * 
	 * @param id
	 * @return User
	 */
	public User searchById(final Long id);

	/**
	 * Método que devuelve el usuario con un "nickname"
	 * 
	 * @param nickName
	 * @return User
	 */
	public User searchByNickName(final String nickName);

	/**
	 * Método que devuelve los usuarios cuyo nickname contenga "%patrón%", ordenados
	 * por lastLogged.
	 * 
	 * @param pattern
	 * @return List<User>
	 */
	public List<User> searchByNickNamePattern(final String pattern);

	/**
	 * Método que devuelve los usuarios cuyo nickname "%patrón%" y que tengan más de
	 * X número de posts publicados, ordenados por lastLogged.
	 * 
	 * @param pattern
	 * @return List<User>
	 */
	public List<User> searchByNickNamePatternAndPosts(final String pattern, final Long posts);

	/**
	 * Método que devuelve todos los registros de la tabla de usuarios.
	 * 
	 * @return List<User>
	 */
	public List<User> searchAll();

	/**
	 * Método que realiza el proceso de registrar un usuario. Solo es necesario
	 * especificar el "nickname" y la contraseña a la que se le va a generar el
	 * hash. Los demás atributos los auto-completará este método.
	 * 
	 * @param nickname
	 * @param password
	 * @param auditCred
	 * @return User
	 */
	public User registerUser(final String nickname, final String password, final AuditCredentials auditCred);

	/**
	 * Método que realizará el proceso de inicio de sesión a un usuario. Hará un
	 * hash a la contraseña que se pasa como parámetro y comprobará que ambos hashes
	 * sean iguales. En caso de que falle la comprobación devolverá "false". En caso
	 * de que NO falle, actualizará la fecha de último acceso a la cuenta
	 * (LAST_LOGGED).
	 * 
	 * @param nickName
	 * @param password
	 * @param auditCred
	 * @return boolean
	 */
	public boolean loginUser(final String nickName, final String password, final AuditCredentials auditCred);

	/**
	 * Método para seguir a un usuario. En caso de seguirse a sí mismo, se lanzará
	 * una excepción.
	 * 
	 * @param followUser
	 * @param user
	 * @param auditCred
	 */
	public void followUser(final User followUser, final User user, final AuditCredentials auditCred)
			throws UserManagementServiceException;

	/**
	 * Método para dejar de seguir a un usuario.
	 * 
	 * @param unfollowUser
	 * @param user
	 * @param auditCred
	 */
	public void unfollowUser(final User unfollowUser, final User user, final AuditCredentials auditCred);
}
