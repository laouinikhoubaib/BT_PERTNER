package tn.spring.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PostShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String content;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Posts post;

    public PostShare()
    {  super(); }

    public PostShare(String content, User user, Posts post) {
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public PostShare(int id, String content, User user, Posts post) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }
}
