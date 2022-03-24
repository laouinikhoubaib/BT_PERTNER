package tn.spring.esprit.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Comments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Comment_id;
	@Temporal(TemporalType.DATE)
	private Date CommentDate;

	private String CommentContent;

	
	@ManyToOne()
	@JsonIgnore
	private User user;
	
	@ManyToOne()
	@JsonIgnore
	private Posts posts;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "comment")
	private Set<CommentReaction> listCommentReactions;

	public int getComment_id() {
		return Comment_id;
	}

	public void setComment_id(int comment_id) {
		Comment_id = comment_id;
	}

	public Date getCommentDate() {
		return CommentDate;
	}

	public void setCommentDate(Date commentDate) {
		CommentDate = commentDate;
	}

	public String getCommentContent() {
		return CommentContent;
	}

	public void setCommentContent(String commentContent) {
		CommentContent = commentContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	public Set<CommentReaction> getListCommentReactions() {
		return listCommentReactions;
	}

	public void setListCommentReactions(Set<CommentReaction> listCommentReactions) {
		this.listCommentReactions = listCommentReactions;
	}

	public Comments(Date commentDate, String commentContent, User user, Posts posts) {
		CommentDate = commentDate;
		CommentContent = commentContent;
		this.user = user;
		this.posts = posts;
	}

	public Comments() {
		super();
	}
}
