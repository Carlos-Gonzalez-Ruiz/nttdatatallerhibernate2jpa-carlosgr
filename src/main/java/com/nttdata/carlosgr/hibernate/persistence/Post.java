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
 * Clase para la tabla de publicaciones / "post".
 * 
 * @author NTT Data - Carlos González Ruiz
 */
@Entity
@Table(name = "T_POST", schema = "nttdata_hibernate_taller2_jpa_carlosgr_schema")
public class Post extends AbstractEntity implements Serializable {

	/** Serial version ID */
	private static final long serialVersionUID = 1L;

	/** ID (Clave primaria) */
	private Long postId;
	/** Titulo del video */
	private String title;
	/** URL al video */
	private String urlVideo;
	/** Descripción del video */
	private String description;
	/** Fecha de publicación */
	private Date firstPublished;
	/** Fecha en la que se realizo una edición por última vez */
	private Date lastEdited;

	/** Votos al post */
	private List<Vote> votes;

	/** Usuario que ha publicado el video */
	private User user;
	/** Lista de comentarios */
	private List<Comment> comments;

	/**
	 * @return the postId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	public Long getPostId() {
		return postId;
	}

	/**
	 * @param postId the postId to set
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	/**
	 * @return the title
	 */
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the urlVideo
	 */
	@Column(name = "URL_VIDEO")
	public String getUrlVideo() {
		return urlVideo;
	}

	/**
	 * @param urlVideo the urlVideo to set
	 */
	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	/**
	 * @return the description
	 */
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
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
	 * @return the comments
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Transient
	@Override
	public Long getId() {
		return postId;
	}

	@Override
	public void setId(Long id) {
		this.postId = id;
	}

	@Override
	public String toString() {
		/* @formatter:off */
		return "[postId=" + postId + ", " + "title=\"" + title + "\", " + "urlVideo=\"" + urlVideo + "\", "
				+ "description=\"" + description + "\", " + "firstPublished=\"" + firstPublished.toString() + "\", "
				+ "lastEdited=\"" + (lastEdited == null ? "null" : lastEdited.toString()) + "\", " + "votes="
				+ votes.toString() + ", " + "user=" + user.getUserId().toString() + ", " + "comments="
				+ comments.toString() + "]";
		/* @formatter:on */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(comments, description, firstPublished, lastEdited, postId, title, urlVideo, user, votes);
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
		Post other = (Post) obj;
		return Objects.equals(comments, other.comments) && Objects.equals(description, other.description)
				&& Objects.equals(firstPublished, other.firstPublished) && Objects.equals(lastEdited, other.lastEdited)
				&& Objects.equals(postId, other.postId) && Objects.equals(title, other.title)
				&& Objects.equals(urlVideo, other.urlVideo) && Objects.equals(user, other.user)
				&& Objects.equals(votes, other.votes);
	}

}
