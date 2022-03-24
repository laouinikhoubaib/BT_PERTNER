package tn.spring.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.spring.esprit.entities.Posts;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
public class PostReaction implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    int id;


    @ManyToOne
    private Posts post;

    @Enumerated(EnumType.STRING)
    private TypeReaction typereaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public TypeReaction getTypereaction() {
        return typereaction;
    }

    public void setTypereaction(TypeReaction typereaction) {
        this.typereaction = typereaction;
    }

    public PostReaction(Posts post, TypeReaction typereaction) {
        this.post = post;
        this.typereaction = typereaction;
    }

    public PostReaction() {
        super();
    }




}
