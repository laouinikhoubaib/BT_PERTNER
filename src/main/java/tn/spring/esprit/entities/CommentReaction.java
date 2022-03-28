package tn.spring.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class CommentReaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    int id;

    @ManyToOne
    private Comments comment;

    @ManyToOne()
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private TypeReaction typereaction;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comments getComment() {
        return comment;
    }

    public TypeReaction getTypereaction() {
        return typereaction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComments(Comments comment) {
        this.comment = comment;
    }

    public void setTypereaction(TypeReaction typereaction) {
        this.typereaction = typereaction;
    }

    public CommentReaction(Comments comment, TypeReaction typereaction) {
        this.comment = comment;
        this.typereaction = typereaction;
    }

    public int getId() {
        return id;
    }

    public CommentReaction() {
        super();
    }

}
