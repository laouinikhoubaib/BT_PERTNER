package tn.spring.esprit.entities;

import javax.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String Object;

    @Column
    String Body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return Object;
    }

    public void setObject(String object) {
        Object = object;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public Feedback() {
        super();
    }

    public Feedback(String object, String body) {
        Object = object;
        Body = body;
    }
}
