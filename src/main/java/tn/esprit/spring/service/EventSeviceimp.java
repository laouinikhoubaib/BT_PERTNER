package tn.esprit.spring.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.StringValueExp;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

import antlr.debug.Event;
import tn.esprit.spring.entities.Eventparticipants;
import tn.esprit.spring.entities.Events;
import tn.esprit.spring.entities.News;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.EventStatsRepository;
import tn.esprit.spring.repository.EventparticipantsRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class EventSeviceimp implements EventSevice{
	@Autowired
	EventRepository ur ;
	@Autowired
	UserRepository rr ;
	@Autowired
	EventStatsRepository er ;
	@Autowired
	EventparticipantsRepository urp ;

	    @Autowired
	    private JavaMailSender emailSender;
	
	    public Events ajouterEvent(Events c) {
		ur.save(c)	;
		return c;
	}
	
	@Autowired
    private EventRepository repo;
     
    public List<Events> getallEvent() {
    	
    	List<tn.esprit.spring.entities.Events> events = new ArrayList<tn.esprit.spring.entities.Events>();  
    	repo.findAll().forEach(event -> events.add(event));  
    	return events;  
    }
    
    public void delete(int idEvent)   
    {  
    	
    ur.deleteById(idEvent);  
    }  
    
    public void update(tn.esprit.spring.entities.Events events)   
    {  
    ur.save(events);  
    SimpleMailMessage message = new SimpleMailMessage();

    String y = String.valueOf(events.getEvent_id());
	List<Eventparticipants> ep =filee(y);
	Eventparticipants z = new Eventparticipants() ;
	   int i ;
	 for(i=0;i<ep.size() ;i++) {
		   z =ep.get(i);
		   String m = z.getUseremail();
    message.setFrom("medamnkdr1999@gmail.com");
    message.setTo(m);
    message.setSubject("hi , event is updated ");
    message.setText("event title:"+events.getEventTitle()+"  event lieu" +events.getLieu()+ "event date"+events.getEventDate());

        ms.send(message);
	 }  
    }  
    
    
    
    public Events getEvById(int id)   
    {  
    return ur.findById(id).get();  
    }  
    public void nbIncrement (int idEvent) {
    	Events e = ur.findById(idEvent).get();
    	e.setNb(e.getNb()+1);

    	
    }
    
    
    @Autowired
    private JavaMailSender ms ;
    public void sendMail(String to, String a , Date b , String n) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("medamnkdr1999@gmail.com");
        message.setTo(to);
        message.setSubject("hi"+n);
        message.setText("you are participated int this event"  +a+  "see you at "  +b);

            ms.send(message);
       

    }
    
    
    
    
    public boolean verifier(String idUser , String idEvent) {
		
    	List<Eventparticipants> f =fila (idUser , idEvent);
    	if(f==null || f.isEmpty()) {
    	return true;
    	}
    	return false ;
    	
    }
    
    

	

    public void participer(int idUser , int idEvent ) {
    		

    	Events e = ur.findById(idEvent).get();	
    	int a= e.getNb();
    	User u = rr.findById(idUser).get();	
    	String subject="hi";
    	 String text="hi";
    	 String p =String.valueOf(u.getUser_id() );
    	 String b= String.valueOf( idEvent);
    	 String y=p+b ;
    	while(a < e.getNbmax() && JourRest(idEvent)>1) {
    		Eventparticipants ep = new Eventparticipants() ;
    		 String k =String.valueOf(idUser );
        	 String l= String.valueOf( idEvent);
    		if(verifier(l, k)) {
    		e.setNb(a+1);
    		e.getUsers().add(u);
    		ur.save(e);
    		sendMail(u.getEmail(),e.getEventTitle(),e.getEventDate(),u.getFulltName());
    		a ++;
    		
    		ep.setIdEv(String.valueOf(e.getEvent_id()));;
    		ep.setUseremail(u.getEmail());
    		ep.setUserename(u.getFulltName());
    		ep.setIduser(String.valueOf(u.getUser_id()));
    		ep.setInscriptiondate(java.time.LocalDate.now());
    		urp.save(ep)	;
    		}
    		else {
    		System.out.println("deja exist");
    		}
    	break;
    }
		

}
   
    public void reminder (int idEvent){
    	Events e = ur.findById(idEvent).get();	
		String y = String.valueOf(idEvent);
		List<Eventparticipants> ep =filee(y);
		Eventparticipants z = new Eventparticipants() ;
	   int i ;
	   for(i=0;i<ep.size() ;i++) {
		   z =ep.get(i);
		   String m = z.getUseremail();
		   String a= "reminder";
		   float b = JourRest ( idEvent) ;
		   String n=(e.getEventTitle()+"is after "+JourRest ( idEvent));
		   SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("medamnkdr1999@gmail.com");
	        message.setTo(m);
	        message.setSubject(a);
	        message.setText(n);

	            ms.send(message);
	   }
	   
	   
		
	    	
	    }
    
    public void annulerlaparticipation(int idUser , int idEvent ) {
    	
    	Events e = ur.findById(idEvent).get();	
    	int a= e.getNb();
    	User u = rr.findById(idUser).get();	
    
    	 String p =String.valueOf(u.getUser_id() );
    	 String b= String.valueOf( idEvent);
    	 String y=p+b ;
    	while(JourRest(idEvent)>1 ) {
    		Eventparticipants ep = new Eventparticipants() ;
    		 String k =String.valueOf(idUser );
        	 String l= String.valueOf( idEvent);
    		if(verifier(l, k)==false) {
    		e.setNb(a-1);
    		ur.save(e);
    		sendMail(u.getEmail(),e.getEventTitle(),e.getEventDate(),u.getFulltName());
    		
    		
        	List<Eventparticipants> f =fila (l , k);
        	 for (int i = 0; i < f.size(); i++) {
        		 Eventparticipants m=f.get(i) ;
        	 urp.delete(m);
        	 }
    		a++ ;

    		}
    		else {
    		System.out.println("you are not participated in this event");
    		}
    	break;
    }
		
    	
    	
    	
    	
    }
    


    public List<Events> find (String abc){
		
 	   
 	   return ur.search(abc).stream().sorted(Comparator.comparing(Events::getEventTitle)).sorted(Comparator.comparing(Events::getEventTitle))
 				.collect(Collectors.toList());
   
 }

    
    public float JourRest (int idEvent){
    	Events e = ur.findById(idEvent).get();
    	Date a = e.getEventDate();
    	LocalDate date1 = (java.time.LocalDate.now());
    	float res =1 ;
  	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
  	String formattedString = date1.format(formatter);
  	  
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");  
    String strDate = format.format(a); 
  	
  	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	   try {
	       Date dateAvant = sdf.parse(formattedString);
	       Date dateApres = sdf.parse(strDate);
	       long diff = dateApres.getTime() - dateAvant.getTime();
	        res = (diff / (1000*60*60*24));
	       System.out.println("Event will be after : "+res);

	   } catch (Exception ex) {
	       ex.printStackTrace();
	   }
	   	return res;

    } 	   
	   
	   public List<Events> fil (String abc){
		
		   return ur.fil(abc).stream().sorted(Comparator.comparing(Events::getEventTitle))
					.collect(Collectors.toList());
	    
    }
 public List<Eventparticipants> filee (String id){
		
		   
		   return urp.filee(id).stream().sorted(Comparator.comparing(Eventparticipants::getIdEv))
					.collect(Collectors.toList());
	    
    }
 public List<Eventparticipants> fila (String ide , String idu){
		
	   
	   return urp.fila(ide,idu).stream().sorted(Comparator.comparing(Eventparticipants::getIdEv)).sorted((Comparator.comparing(Eventparticipants::getIdEv)))
				.collect(Collectors.toList());
  
}
 



 
	    public void send (String to, String a , Date b , String n) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("medamnkdr1999@gmail.com");
	        message.setTo(to);
	        message.setSubject("hi"+n);
	        message.setText("you are participated int this event"  +a+  "see you at "  +b);

	            ms.send(message);
	       

	    }
    
	  
    
    
    public float presenceporcentage (int idEvent) {
    	float a = 0 ;
    	Events e = ur.findById(idEvent).get();
    	a = e.getNb()*100/e.getNbmax();
    	
    	return a ;
    }
    
    
public List<String> eventsStat (int idEvent){
	List<String> l = new ArrayList ();
	Events e = ur.findById(idEvent).get();
	Date a = e.getEventDate();
	l.add(e.getEventTitle());
	l.add ("presence pourcentage : " + presenceporcentage ( idEvent));
	l.add ("unoccuped places " + String.valueOf(e.getNbmax()-e.getNb()));
	return l;
    	
    }









    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
