package tn.spring.esprit.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.esprit.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{

}