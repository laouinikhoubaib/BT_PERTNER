package tn.spring.esprit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tn.spring.esprit.entities.Feedback;
import tn.spring.esprit.service.FeedbackService;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping ("/feedback")
public class FeedbackController {


    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(value="/addFeedback", method = RequestMethod.POST)
    public Feedback saveFeedback(@RequestBody  Feedback feedback) throws SQLIntegrityConstraintViolationException{

        return feedbackService.save(feedback);
    }

}

