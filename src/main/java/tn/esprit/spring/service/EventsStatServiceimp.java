package tn.esprit.spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Events;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.EventStatsRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class EventsStatServiceimp implements EventsStatService {
	@Autowired
	EventRepository er ;
	@Autowired
	UserRepository rr ;
	@Autowired
	EventStatsRepository esr ;
	public void eventsStat (int idEvent){
		Events es = er.findById(idEvent).get();

		Events e = er.findById(idEvent).get();
		Date a = e.getEventDate();
	    	
	    }
}
