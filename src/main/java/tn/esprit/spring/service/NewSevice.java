package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Eventparticipants;
import tn.esprit.spring.entities.FileDB;
import tn.esprit.spring.entities.News;


public interface NewSevice {
	public tn.esprit.spring.entities.News ajouterNew(tn.esprit.spring.entities.News c) ;
	public List<tn.esprit.spring.entities.News> getallNew();
    public void delete(int News_id)  ;
    public void update(tn.esprit.spring.entities.News event);
    public News getNsById(int id) ;  
    public float rating(int a, int News_id);
    public void incrementViews(Integer News_id);
    public void sendSimpleMail(String to, String subject, String content);
    public void ajouterdocument(int id , int News_id );
    public void enregistrerdocumentdansnew (int News_id , int id );
    public List<News> find (String abc);
	public float JourRest(int idnews);
	public List<News> filedactulites ();

}
