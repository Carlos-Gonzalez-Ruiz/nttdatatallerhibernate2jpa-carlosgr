package com.nttdata.carlosgr.hibernate;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.carlosgr.hibernate.audit.AuditCredentials;
import com.nttdata.carlosgr.hibernate.persistence.Comment;
import com.nttdata.carlosgr.hibernate.persistence.Post;
import com.nttdata.carlosgr.hibernate.persistence.User;
import com.nttdata.carlosgr.hibernate.persistence.Vote;
import com.nttdata.carlosgr.hibernate.service.implementations.CommentManagementServiceImpl;
import com.nttdata.carlosgr.hibernate.service.implementations.PostManagementServiceImpl;
import com.nttdata.carlosgr.hibernate.service.implementations.UserManagementServiceImpl;
import com.nttdata.carlosgr.hibernate.service.implementations.VoteManagementServiceImpl;
import com.nttdata.carlosgr.hibernate.service.interfaces.CommentManagementServiceI;
import com.nttdata.carlosgr.hibernate.service.interfaces.PostManagementServiceI;
import com.nttdata.carlosgr.hibernate.service.interfaces.UserManagementServiceI;
import com.nttdata.carlosgr.hibernate.service.interfaces.VoteManagementServiceI;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase que realiza todas las inserciones de datos de prueba.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class InsertionDemo {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(InsertionDemo.class);

	/** Objeto de sesión */
	private static Session session;

	/** Credenciales de la sesión */
	private static AuditCredentials auditCred;

	/** Servicio de gestión de la tabla de usuarios. */
	private static UserManagementServiceI userService;
	/** Servicio de gestión de la tabla de posts. */
	private static PostManagementServiceI postService;
	/** Servicio de gestión de la tabla de comentarios. */
	private static CommentManagementServiceI commentService;
	/** Servicio de gestión de la tabla de votos. */
	private static VoteManagementServiceI voteService;

	/**
	 * Constructor de la clase.
	 * 
	 */
	private InsertionDemo() {
		super();
	}

	/**
	 * Método que se encarga de insertar todos los datos de prueba.
	 * 
	 */
	public static void insertData() {
		// Inicializar servicios.
		userService = new UserManagementServiceImpl(session);
		postService = new PostManagementServiceImpl(session);
		commentService = new CommentManagementServiceImpl(session);
		voteService = new VoteManagementServiceImpl(session);

		// Registrar usuarios.
		userService.registerUser("ball8", "1234", auditCred);
		userService.registerUser("seduNdneS", "1234", auditCred);
		userService.registerUser("miku03", "1234", auditCred);

		User user;

		// Simular usuario 1
		user = userService.searchByNickName("ball8");
		if (userService.loginUser(user.getNickName(), "1234", auditCred)) {

			// Seguir usuario
			userService.followUser(userService.searchByNickNamePattern("%seduN%").get(0), user, auditCred);
			userService.followUser(userService.searchByNickNamePattern("%miku03%").get(0), user, auditCred);

			// Postear
			Post post = postService.uploadPost("Irony", "https://www.youtube.com/watch?v=UmJ5Tf6G0FQ",
					"Oh the irony of life... By Scop", user, auditCred);

			// Escribir comentario
			Comment comment = commentService.writeCommentPost(post, "This is my first post!!!! :DDD", user, auditCred);
			commentService.replyComment(comment, "And also my first comment yay ^.^ ", user, auditCred);

			// Dar voto
			voteService.voteUser(userService.searchByNickNamePattern("%miku%").get(0), Vote.VoteType.UPVOTE, user,
					auditCred);
		}

		// Simular usuario 2
		user = userService.searchByNickName("seduNdneS");
		if (userService.loginUser(user.getNickName(), "1234", auditCred)) {

			// Seguir usuario
			userService.followUser(userService.searchByNickNamePatternAndPosts("%ball%", Long.valueOf(0)).get(0), user,
					auditCred);

			// Postear
			postService.uploadPost("Terry A Davis gets a phonecall", "https://www.bitchute.com/video/77hXXHzPYjH7/", "",
					user, auditCred);

			// Escribir comentario
			commentService.writeCommentUser(
					userService.searchByNickNamePatternAndPosts("%ball%", Long.valueOf(0)).get(0),
					"Hey man, long time no see!<br>Hope you are still doing great<br>That song you posted is such a good banger!",
					user, auditCred);

			// Dar voto
			voteService.voteUser(userService.searchByNickNamePattern("%ball8%").get(0), Vote.VoteType.UPVOTE, user,
					auditCred);
			voteService.voteUser(userService.searchByNickNamePattern("%miku%").get(0), Vote.VoteType.UPVOTE, user,
					auditCred);
		}

		// Simular usuario 3
		user = userService.searchByNickName("miku03");
		if (userService.loginUser(user.getNickName(), "1234", auditCred)) {

			// Postear
			postService.uploadPost("Mou ikkai!!", "https://embed.nicovideo.jp/watch/sm4762074",
					"Some random video I found on NicoNico Video", user, auditCred);

			// Escribir comentario
			commentService.writeCommentPost(postService.searchByTitleDescription("%Irony%").get(0), "Thats me! >:o",
					user, auditCred);

			// Dar voto
			voteService.votePost(postService.searchByTitleDescription("%Irony%").get(0), Vote.VoteType.DOWNVOTE, user,
					auditCred);
		}

		// NOTA: Hay que hacer un session.clear() para poder obtener la lista de objetos
		// en las relaciones.
		session.clear();
	}

	/**
	 * Método que muestra los datos que han sido insertados.
	 * 
	 */
	public static void showData() {
		StringBuilder list;

		// Mostrar usuarios.
		list = new StringBuilder();
		for (User u : userService.searchAll()) {
			list.append(u.toString() + "\n");
		}
		LOG.info("\n > SELECT * FROM T_USER\n{}\n", list);

		// Mostrar posts.
		list = new StringBuilder();
		for (Post p : postService.searchAll()) {
			list.append(p.toString() + "\n");
		}
		LOG.info("\n > SELECT * FROM T_POST\n{}\n", list);

		// Mostrar comentarios.
		list = new StringBuilder();
		for (Comment c : commentService.searchAll()) {
			list.append(c.toString() + "\n");
		}
		LOG.info("\n > SELECT * FROM T_COMMENT\n{}\n", list);

		// Mostrar votos.
		list = new StringBuilder();
		for (Vote v : voteService.searchAll()) {
			list.append(v.toString() + "\n");
		}
		LOG.info("\n > SELECT * FROM T_VOTE\n{}\n", list);
	}

	/**
	 * @return the session
	 */
	public static Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public static void setSession(Session session) {
		InsertionDemo.session = session;
	}

	/**
	 * @return the auditCred
	 */
	public static AuditCredentials getAuditCred() {
		return auditCred;
	}

	/**
	 * @param auditCred the auditCred to set
	 */
	public static void setAuditCred(AuditCredentials auditCred) {
		InsertionDemo.auditCred = auditCred;
	}
}