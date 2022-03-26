package tn.esprit.spring.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfTable;

import tn.esprit.spring.entities.Eventparticipants;
import tn.esprit.spring.entities.Events;


public interface EventSevice {
	public tn.esprit.spring.entities.Events ajouterEvent(tn.esprit.spring.entities.Events c) ;
	public List<tn.esprit.spring.entities.Events> getallEvent();
    public void delete(int id)  ;
    public void update(tn.esprit.spring.entities.Events events);
    public Events getEvById(int id)   ;
    public void participer(int idUser , int idEvent) ;
    public float JourRest (int idEvent);
    public List<String> eventsStat (int idEvent);
    public List<Events>  fil(String abc );
    public void sendMail(String to, String a , Date b , String n);
    public List<Eventparticipants>  filee(String id );
    public List<Eventparticipants> fila (String ide , String idu);
    public boolean verifier(String idUser , String idEvent);
    
    public void annulerlaparticipation(int idUser , int idEvent) ;
    public void reminder (int idEvent);
    public List<Events> find (String abc);
}
