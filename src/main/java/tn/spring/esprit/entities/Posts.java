package tn.spring.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Posts
{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int postId;
	
	@Temporal(TemporalType.DATE)
	private Date postDate;
	
	private String postContent;


	
	@Enumerated(EnumType.STRING)
	private Dictionary dictionary ;
	
	@ManyToOne()
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="posts")
	@JsonIgnore
	private Set<Comments>comments;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
	private Set<PostReaction> listPostsReactions;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public Set<PostReaction> getListPostsReactions() {
		return listPostsReactions;
	}

	public void setListPostsReactions(Set<PostReaction> listPostsReactions) {
		this.listPostsReactions = listPostsReactions;
	}

}
