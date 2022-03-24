package tn.spring.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PostAttachement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @Column
    String type;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Posts post;

    public PostAttachement() {
        super();
    }
    public PostAttachement(String content, String type) {
        this.name = content;
        this.type = type;
    }

    public PostAttachement(int id, String content, String type) {
        this.id = id;
        this.name = content;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }
    @Override
    public String toString() {
        return "PostAttachement{" +
                "id=" + id +
                ", content='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
