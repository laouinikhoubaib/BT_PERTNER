package tn.spring.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.esprit.entities.Notification;
import tn.spring.esprit.service.NotificationService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @RequestMapping(value="/addNotif", method = RequestMethod.POST)
    public Notification saveNotification(@RequestBody  Notification notification) throws SQLIntegrityConstraintViolationException {
        System.out.println(notification);
        return notificationService.save(notification);
    }

    @RequestMapping(value="/updateNotif/{id}", method = RequestMethod.PUT)
    public Notification updateNotification(@RequestBody Notification NewNotif,@PathVariable (value = "id") int id ) {
        System.out.println(id);
        return notificationService.update(NewNotif ,id);
    }

    @RequestMapping(value="/findAllNotif", method = RequestMethod.GET)
    public List<Notification> findAllNotification() {
        return notificationService.findAll();
    }


    @RequestMapping(value="/deleteNotif/{id}", method = RequestMethod.DELETE)
    public String deleteNotification(@PathVariable(value = "id") int id) {
        System.out.println(id);
        notificationService.delete(id);
        return ("Notification succesfuly deleted");
    }

}
