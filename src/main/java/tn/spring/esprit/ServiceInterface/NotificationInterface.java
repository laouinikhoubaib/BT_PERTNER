package tn.spring.esprit.ServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.esprit.entities.Notification;
import tn.spring.esprit.repository.NotificationRepository;

import java.util.List;

public interface NotificationInterface  {


    Notification save(Notification notif);

    Notification update(Notification NewNotif ,int id);

    List<Notification> findAll();

    void delete(Integer id);

    String affecterNotficationUser (int post_id,int user_id,int notification_id );



}