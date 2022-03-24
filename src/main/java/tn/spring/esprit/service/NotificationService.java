package tn.spring.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.esprit.ServiceInterface.NotificationInterface;
import tn.spring.esprit.entities.Notification;
import tn.spring.esprit.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationService implements NotificationInterface {

    @Autowired
    NotificationRepository notificationRepository;

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

}
