package com.nttdata.carlosgr.hibernate.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase para la tabla de voto.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
@Entity
@Table(name = "T_VOTE", schema = "nttdata_hibernate_taller2_jpa_carlosgr_schema")
public class Vote extends AbstractEntity implements Serializable {
	public enum VoteType {
		UPVOTE, DOWNVOTE
		/*
		 * Tipos posibles a añadir: - LAUGH (indicar que es divertido) - SAD (indicar
		 * que es triste)
		 */
	}

	/** Serial Version ID */
	private static final long serialVersionUID = 1L;

	/** ID (Clave primaria) */
	private Long voteId;
	/** Tipo de voto */
	private VoteType type;

	/** Usuario que ha dado el voto */
	private User byUser;
	/** Cuenta de usuario a que va dirigido el voto */
	private User user;
	/** Post al que va dirgido el voto */
	private Post post;
	/** Comentario al que va dirgido el voto */
	private Comment comment;

	/**
	 * @return the voteId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VOTE_ID")
	public Long getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId the voteId to set
	 */
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	/**
	 * @return the type
	 */
	@Column(name = "TYPE", nullable = false)
	@NotEmpty
	public VoteType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(VoteType type) {
		this.type = type;
	}

	/**
	 * @return the byUser
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BYUSER_ID")
	public User getByUser() {
		return byUser;
	}

	/**
	 * @param byUser the byUser to set
	 */
	public void setByUser(User byUser) {
		this.byUser = byUser;
	}

	/**
	 * @return the user
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the post
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "POST_ID")
	public Post getPost() {
		return post;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(Post post) {
		this.post = post;
	}

	/**
	 * @return the comment
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMMENT_ID")
	public Comment getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Transient
	@Override
	public Long getId() {
		return voteId;
	}

	@Override
	public void setId(Long id) {
		this.voteId = id;
	}

	@Override
	public String toString() {
		String foreignKeyString;

		// Crear string para mostrar a qué usuario, post o comentario pertenece.
		if (comment == null) {
			if (post == null) {
				foreignKeyString = user == null ? "" : ("user=" + user.getUserId().toString());
			} else {
				foreignKeyString = "post=" + post.getPostId().toString();
			}
		} else {
			foreignKeyString = "replyingTo=" + comment.getCommentId().toString();
		}

		/* @formatter:off */
		return 	"[voteId=" + voteId.toString() + ", " +
				"type=\"" + type.toString() + "\", " +
				"byUser=\"" + byUser.getUserId().toString() + "\", " +
				foreignKeyString + "]";
		/* @formatter:on */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(byUser, comment, post, type, user, voteId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		return Objects.equals(byUser, other.byUser) && Objects.equals(comment, other.comment)
				&& Objects.equals(post, other.post) && type == other.type && Objects.equals(user, other.user)
				&& Objects.equals(voteId, other.voteId);
	}

}
