package tn.spring.esprit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    int id;

    @Column
    String objetNotif;

    @Column
    Boolean status;


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
