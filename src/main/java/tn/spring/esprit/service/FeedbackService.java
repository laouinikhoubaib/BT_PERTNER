package tn.spring.esprit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.esprit.ServiceInterface.FeedbackInterface;
import tn.spring.esprit.entities.Feedback;
import tn.spring.esprit.repository.FeedbackRepository;

@Service
public class FeedbackService implements FeedbackInterface {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }


}




