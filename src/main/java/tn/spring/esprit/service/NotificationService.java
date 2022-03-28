package tn.spring.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.esprit.ServiceInterface.NotificationInterface;
import tn.spring.esprit.entities.*;
import tn.spring.esprit.repository.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NotificationService implements NotificationInterface {

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    PostsRepository postrepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostReactionReposirory postReactionReposirory;
    @Autowired
    CommentReactionRepository commentReacRep;
    @Autowired
    CommentRepository comr;

    @Override
    public Notification save(Notification notif) {
        return notificationRepository.save(notif);
    }

    @Override
    public Notification update(Notification NewNotif ,int id){
        Notification Notif = notificationRepository.findById(id).orElse(null);
        Notif.setObjetNotif(NewNotif.getObjetNotif());
        Notif.setStatus(NewNotif.getStatus());


        notificationRepository.save(Notif);
        return Notif;
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }


    @Override
    public void delete(Integer id){
        notificationRepository.deleteById(id);
    }

@Override
    public String affecterNotficationUser (int post_id,int user_id,int notification_id ) {
    User user = userRepository.findById(user_id).orElse(null);
    Posts post = postrepository.findById(post_id).orElse(null);

    Notification notification= notificationRepository.findById(notification_id).orElse(null);
    if (notification != null){
        notification.setUser(user);
        notificationRepository.save(notification);
    }

    if (user.getNotifications()==null){
        Set<Notification> notifications = new HashSet<>();
        notifications.add(notification);
        user.setNotifications(notifications);
        userRepository.save(user);
    }else {
        user.getNotifications().add(notification);
        userRepository.save(user);
    }
    return ("l'utlisateur "+ user.getFirstName() + " " +user.getLastName() + " a fait sur votre post ");



}



}
