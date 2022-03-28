package tn.spring.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    int id;

    @Column
    String objetNotif;

    @Column
    Boolean status;

    @ManyToOne()
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjetNotif() {
        return objetNotif;
    }

    public void setObjetNotif(String objetNotif) {
        this.objetNotif = objetNotif;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Notification() {
        super();
    }

    public Notification(String objetNotif, Boolean status) {
        this.objetNotif = objetNotif;
        this.status = status;
    }


}
