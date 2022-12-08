package com.nttdata.carlosgr.hibernate.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase para la tabla de comentarios.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
@Entity
@Table(name = "T_COMMENT", schema = "nttdata_hibernate_taller2_jpa_carlosgr_schema")
public class Comment extends AbstractEntity implements Serializable {

	/** Serial version ID */
	private static final long serialVersionUID = 1L;

	/** ID (Clave primaria) */
	private Long commentId;
	/** Contenido del comentario */
	private String content;
	/** Fecha de publicación */
	private Date firstPublished;
	/** Fecha en la que se realizo una edición por última vez */
	private Date lastEdited;

	/** Votos al comentario */
	private List<Vote> votes;

	/** Cuenta en la que se ha escrito el comentario */
	private User user;
	/** Post en el que se ha escrito el comentario */
	private Post post;
	/** Comentario al que responde */
	private Comment replyingTo;
	/** Comentarios que responde a este comentario */
	private List<Comment> replies;
	/** Usuario que ha escrito el comentario */
	private User byUser;

	/**
	 * @return the commentId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID")
	public Long getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the content
	 */
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the firstPublished
	 */
	@Column(name = "FIRST_PUBLISHED")
	public Date getFirstPublished() {
		return firstPublished;
	}

	/**
	 * @param firstPublished the firstPublished to set
	 */
	public void setFirstPublished(Date firstPublished) {
		this.firstPublished = firstPublished;
	}

	/**
	 * Método que devuelve la fecha en la que se editó por última vez. Indica que el
	 * contenido del post sido editado si "lastEdited" no es nulo.
	 * 
	 * @return the lastEdited
	 */
	@Column(name = "LAST_EDITED")
	public Date getLastEdited() {
		return lastEdited;
	}

	/**
	 * @param lastEdited the lastEdited to set
	 */
	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}

	/**
	 * @return the votes
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
	public List<Vote> getVotes() {
		return votes;
	}

	/**
	 * @param votes the votes to set
	 */
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
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
	 * @return the replyingTo
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REPLYING_TO_ID")
	public Comment getReplyingTo() {
		return replyingTo;
	}

	/**
	 * @param replyingTo the replyingTo to set
	 */
	public void setReplyingTo(Comment replyingTo) {
		this.replyingTo = replyingTo;
	}

	/**
	 * @return the replies
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "replyingTo")
	public List<Comment> getReplies() {
		return replies;
	}

	/**
	 * @param replies the replies to set
	 */
	public void setReplies(List<Comment> replies) {
		this.replies = replies;
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

	@Transient
	@Override
	public Long getId() {
		return this.commentId;
	}

	@Override
	public void setId(Long id) {
		this.commentId = id;
	}

	@Override
	public String toString() {
		String foreignKeyString;

		// Crear string para mostrar a qué usuario, post o comentario (respuesta a
		// comentario) pertenece.
		if (replyingTo == null) {
			if (post == null) {
				foreignKeyString = user == null ? "" : ("user=" + user.getUserId().toString());
			} else {
				foreignKeyString = "post=" + post.getPostId().toString();
			}
		} else {
			foreignKeyString = "replyingTo=" + replyingTo.getCommentId().toString();
		}

		/* @formatter:off */
		return 	"[commentId=" + commentId + ", " +
				"content=\"" + content + "\", " +
				"firstPublished=\"" + firstPublished.toString() + "\", " +
				"lastEdited=\"" + (lastEdited == null ? "null" : lastEdited.toString()) + "\", " +
				"votes=" + votes.toString() + ", " +
				foreignKeyString + ", " +
				"replies=" + replies.size() + ", " +
				"byUser=" + byUser.getUserId().toString() + "]";
		/* @formatter:on */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(byUser, commentId, content, firstPublished, lastEdited, post, replies,
				replyingTo, user, votes);
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
		Comment other = (Comment) obj;
		return Objects.equals(byUser, other.byUser) && Objects.equals(commentId, other.commentId)
				&& Objects.equals(content, other.content) && Objects.equals(firstPublished, other.firstPublished)
				&& Objects.equals(lastEdited, other.lastEdited) && Objects.equals(post, other.post)
				&& Objects.equals(replies, other.replies) && Objects.equals(replyingTo, other.replyingTo)
				&& Objects.equals(user, other.user) && Objects.equals(votes, other.votes);
	}

}
