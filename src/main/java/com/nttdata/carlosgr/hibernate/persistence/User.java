package com.nttdata.carlosgr.hibernate.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase para la tabla de cuenta de usuario.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
@Entity
@Table(name = "T_USER", schema = "nttdata_hibernate_taller2_jpa_carlosgr_schema")
public class User extends AbstractEntity implements Serializable {

	/** Serial version ID */
	private static final long serialVersionUID = 1L;

	/** ID (Clave primaria) */
	private Long userId;
	/** Apodo del usuario */
	private String nickName;
	/** Nombre real del usuario */
	private String realName;
	/** URL a la foto de perfil */
	private String urlPfp;
	/** Descripción de la cuenta de usuario */
	private String description;
	/** Hash de la contraseña */
	private String passwordHash;
	/** Fecha en la que inició sesión por primera vez (cuándo se creó la cuenta) */
	private Date firstLogged;
	/** Fecha en la que inició sesión por última vez */
	private Date lastLogged;

	/** Comentarios a la cuenta */
	private List<Comment> comments;
	/** Votos a la cuenta */
	private List<Vote> votes;

	/** Usuarios a los que sigue esta cuenta */
	private Set<User> followers;
	/** Usuarios que siguen esta cuenta */
	private Set<User> following;

	/** Lista de posts publicados */
	private List<Post> posts;
	/** Lista de comentarios publicados */
	private List<Comment> commentsByUser;
	/** Votos que el usuario a dado a un post, cuenta o comentario */
	private Set<Vote> votesByUser;

	/**
	 * @return the userId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the nickName
	 */
	@Column(name = "NICKNAME", nullable = false, unique = true)
	@NotEmpty
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the realName
	 */
	@Column(name = "REALNAME")
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * Método que devuelve la URL a la foto de perfil del usuario. Devuelve nulo en
	 * caso de que no se haya establecido ninguna foto.
	 * 
	 * @return the urlPfp
	 */
	@Column(name = "URL_PFP")
	public String getUrlPfp() {
		return urlPfp;
	}

	/**
	 * @param urlPfp the urlPfp to set
	 */
	public void setUrlPfp(String urlPfp) {
		this.urlPfp = urlPfp;
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
	 * @return the passwordHash
	 */
	@Column(name = "PASSWORD", nullable = false)
	@NotEmpty
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * @return the firstLogged
	 */
	@Column(name = "FIRST_LOGGED")
	public Date getFirstLogged() {
		return firstLogged;
	}

	/**
	 * @param firstLogged the firstLogged to set
	 */
	public void setFirstLogged(Date firstLogged) {
		this.firstLogged = firstLogged;
	}

	/**
	 * @return the lastLogged
	 */
	@Column(name = "LAST_LOGGED")
	public Date getLastLogged() {
		return lastLogged;
	}

	/**
	 * @param lastLogged the lastLogged to set
	 */
	public void setLastLogged(Date lastLogged) {
		this.lastLogged = lastLogged;
	}

	/**
	 * @return the comments
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the votes
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
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
	 * @return the followers
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "T_USER_USER", joinColumns = @JoinColumn(name = "FOLLOWER_ID"), inverseJoinColumns = @JoinColumn(name = "FOLLOWING_ID"))
	public Set<User> getFollowers() {
		return followers;
	}

	/**
	 * @param followers the followers to set
	 */
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	/**
	 * @return the following
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "T_USER_USER", joinColumns = @JoinColumn(name = "FOLLOWING_ID"), inverseJoinColumns = @JoinColumn(name = "FOLLOWER_ID"))
	public Set<User> getFollowing() {
		return following;
	}

	/**
	 * @param following the following to set
	 */
	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	/**
	 * @return the posts
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	public List<Post> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	/**
	 * @return the commentsByUser
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "byUser")
	public List<Comment> getCommentsByUser() {
		return commentsByUser;
	}

	/**
	 * @param comments the commentsByUser to set
	 */
	public void setCommentsByUser(List<Comment> commentsByUser) {
		this.commentsByUser = commentsByUser;
	}

	/**
	 * @return the votesByUser
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "byUser")
	public Set<Vote> getVotesByUser() {
		return votesByUser;
	}

	/**
	 * @param votesByUser the votesByUser to set
	 */
	public void setVotesByUser(Set<Vote> votesByUser) {
		this.votesByUser = votesByUser;
	}

	@Transient
	@Override
	public Long getId() {
		return this.userId;
	}

	@Override
	public void setId(Long id) {
		this.userId = id;
	}

	@Override
	public String toString() {

		// Generar string para mostrar los usuarios a los que se siguen.
		StringBuilder followingString = new StringBuilder();
		followingString.append('[');
		for (int i = 0; i < following.size(); ++i) {
			followingString
					.append(((User) following.toArray()[i]).getNickName() + (i + 1 != following.size() ? ", " : ""));
		}
		followingString.append(']');

		// Generar string para mostrar los seguidores.
		StringBuilder followersString = new StringBuilder();
		followersString.append('[');
		for (int i = 0; i < followers.size(); ++i) {
			followersString
					.append(((User) followers.toArray()[i]).getNickName() + (i + 1 != followers.size() ? ", " : ""));
		}
		followersString.append(']');

		/* @formatter:off */
		return 	"[userId=" + userId + ", " +
				"nickName=\"" + nickName + "\", " +
				"realName=\"" + realName + "\", " +
				"urlPfp=\"" + urlPfp + "\", " +
				"description=\"" + description + "\", " +
				"passwordHash=\"" + passwordHash + "\", " +
				"firstLogged=\"" + firstLogged.toString() + "\", " +
				"lastLogged=\"" + (lastLogged == null ? "null" : lastLogged.toString()) + "\", " +
				"votesByUser=" + votesByUser.toString() + ", " +
				"votes=" + votes.toString() + ", " +
				"followers=" + followersString.toString() + ", " +
				"following=" + followingString.toString() + ", " +
				"posts=" + posts.toString() + ", " +
				"comments=" + comments.toString() + ", " +
				"commentsByUser=" + commentsByUser.toString() + "]";
		/* @formatter:on */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(comments, commentsByUser, description, firstLogged, lastLogged, nickName,
				passwordHash, posts, realName, urlPfp, userId, votes);
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
		User other = (User) obj;
		return Objects.equals(comments, other.comments) && Objects.equals(commentsByUser, other.commentsByUser)
				&& Objects.equals(description, other.description) && Objects.equals(firstLogged, other.firstLogged)
				&& Objects.equals(followers, other.followers) && Objects.equals(following, other.following)
				&& Objects.equals(lastLogged, other.lastLogged) && Objects.equals(nickName, other.nickName)
				&& Objects.equals(passwordHash, other.passwordHash) && Objects.equals(posts, other.posts)
				&& Objects.equals(realName, other.realName) && Objects.equals(urlPfp, other.urlPfp)
				&& Objects.equals(userId, other.userId) && Objects.equals(votes, other.votes)
				&& Objects.equals(votesByUser, other.votesByUser);
	}

}
