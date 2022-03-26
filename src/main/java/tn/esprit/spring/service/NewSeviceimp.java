package tn.esprit.spring.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.query.criteria.internal.expression.function.LengthFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Eventparticipants;
import tn.esprit.spring.entities.Events;
import tn.esprit.spring.entities.FileDB;
import tn.esprit.spring.entities.News;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.FileDBRepository;
import tn.esprit.spring.repository.NewsRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class NewSeviceimp implements NewSevice{
	@Autowired
	NewsRepository ur ;
	@Autowired
	UserRepository nr ;
	@Autowired
	FileDBRepository fr ;
	
	public News ajouterNew(News c) {
		ur.save(c)	;
		return c;
	}
	
	@Autowired
    private NewsRepository repo;
     
    public List<News> getallNew() {
    	List<News> events = new ArrayList<News>();  
    	repo.findAll().forEach(event -> events.add(event));  
    	return events;  
    }
    
    public void delete(int News_id)   
    {  
    ur.deleteById(News_id);  
    }  
    
    public void update(tn.esprit.spring.entities.News events)   
    {  
    ur.save(events);  
    }  
    
    public News getNsById(int id)   
    {  
    return ur.findById(id).get();  
    }  
    
 

 
 
 public void signalerNews(int idUser , int idNews ) {
			
		}
		
		
		
       public float rating(int a , int News_id) {
   		List<Integer> l = new  ArrayList<Integer>() ;

			News n = ur.findById(News_id).get();
			float r=0;
			int s=0;
			while(a<=5 && a>=0) {
				l.add(a);
				for(int i = 0 ;i<l.size();i++) {
					s=s+l.get(i);
					r =  (float) s/l.size();
				}
				n.setRating(r);
	    		ur.save(n);
	    	break;
	    	
			}
			n.setRating(r);
			return r;
			
			
		}
       
       @Autowired
       private JavaMailSender ms ;
       public void sendSimpleMail(String to, String subject, String content) {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom("medamnkdr1999@gmail.com");
           message.setTo(to);
           message.setSubject(subject);
           message.setText(content);

               ms.send(message);
          

       }
       
       @org.springframework.scheduling.annotation.Async
       public void incrementViews(Integer News_id) {
           synchronized(this) {
               News post = ur.findById(News_id).get();
               post.setViews(post.getViews() + 1);
               ur.save(post);
           }
       }   
       public void enregistrerdocumentdansnew (int News_id , int id ) {
      		
    	   News e = ur.findById(News_id).get();	
       	FileDB f = fr.findById(id).get();	
e.getFiles().add(f);       
fr.save(f);	
       		
       		
       		
       		
       		
    	    }
       
       
       
       
       
       
       public List<News> find (String abc){
   		
    	   
    	   return ur.findBySearchTerm(abc).stream().sorted(Comparator.comparing(News::getNewsTitle)).sorted(Comparator.comparing(News::getNewsTitle))
    				.collect(Collectors.toList());
      
    }
       
       public void afiichernewsdetails (int News_id , int id ) {
    	   
    	   
    	   
       }

	public void ajouterdocument(int id, int News_id) {
		// TODO Auto-generated method stub
		
	}
	
	 
    public float JourRest (int News_id){
    	News e = ur.findById(News_id).get();

    	LocalDate a = e.getPublishedat();
    	LocalDate date1 = (java.time.LocalDate.now());
    	float res =1 ;
  	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
  	String formattedString = date1.format(formatter);
  	  
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");  
    String strDate = formatter.format(a); 
  	
  	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	   try {
	       Date dateAvant = sdf.parse(formattedString);
	       Date dateApres = sdf.parse(strDate);
	       long diff =   dateAvant.getTime()-dateApres.getTime();
	        res = (diff / (1000*60*60*24));
	       System.out.println("published before : "+res);

	   } catch (Exception ex) {
	       ex.printStackTrace();
	   }
	   	return res;

    } 	   
	
	public List<News> filedactulites (){
		News z = new News() ;
    	List<News> l = new ArrayList<News>();  
    	if(JourRest (z.getNews_id())<3) {
    		l.add(z);
    	}
		return l;
		
		
	}
	}

       

