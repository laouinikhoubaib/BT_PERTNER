package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lowagie.text.DocumentException;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

import tn.esprit.spring.entities.Eventparticipants;
import tn.esprit.spring.entities.Events;
import tn.esprit.spring.entities.FileDB;
import tn.esprit.spring.entities.News;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.NewsRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.EventSevice;
import tn.esprit.spring.service.FileStorageService;
import tn.esprit.spring.service.NewSevice;
import tn.esprit.spring.service.NewsPDFexporter;
import tn.esprit.spring.service.Participantspdf;
import tn.esprit.spring.service.ResponseFile;
import tn.esprit.spring.service.ResponseMessage;
import tn.esprit.spring.service.StripeClient;
import tn.esprit.spring.service.UserPDFExporter;




@RestController
public class Controller {

@Autowired
EventSevice cs;
@Autowired
NewSevice ns;




//////////////////////////////////////////////////////////////////////Event////////////////////////////////////////////////////////////

@PostMapping("/ajouterEvent")
@ResponseBody
public void ajouterEvent(@RequestBody Events d){
	cs.ajouterEvent(d);
}


@GetMapping("/afficherevents")  
private List<tn.esprit.spring.entities.Events> getAllEvent()   
{  
return cs.getallEvent();  
}  




@DeleteMapping("/deleteEvent/{Event_id}")  
private void deleteEvent(@PathVariable("Event_id") int idEvent)   
{  
cs.delete(idEvent);  

}  

@GetMapping("/JourRestant/{Event_id}")  
private float JourRest(@PathVariable("Event_id") int Event_id)   
{  
return cs.JourRest (Event_id);  
}  





@PutMapping("/updateEVent")  
private Events update(@RequestBody tn.esprit.spring.entities.Events events  )   
{  
cs.update( events);  
return events;  
}  

@GetMapping("/affEvent/{Event_id}")  
private Events getEVs(@PathVariable("Event_id") int Event_id)   
{  
return cs.getEvById(Event_id);  
}  





@PostMapping("/participer/{idEvent}/{idUser}")
@ResponseBody
public void participer( @PathVariable int idUser ,@PathVariable int idEvent){
	 cs.participer(idUser , idEvent);
}
@PostMapping("/annulerlaparticipation/{idEvent}/{idUser}")
@ResponseBody
public void annulerlaparticipation( @PathVariable int idUser ,@PathVariable int idEvent){
	 cs.annulerlaparticipation(idUser , idEvent);
}

@PostMapping("/relinder/{idEvent}")
@ResponseBody
public void relinder( @PathVariable int idEvent){
	 cs.reminder(idEvent);
}
@GetMapping("/users/export/pdf")
public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
    response.setContentType("application/pdf");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());
     
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=events_" + currentDateTime + ".pdf";
    response.setHeader(headerKey, headerValue);
     
    List<Events> inv = cs.getallEvent();
     
    UserPDFExporter exporter = new UserPDFExporter(inv);
    exporter.export(response);
     
}

@PostMapping("/stats/{idEvent}")
@ResponseBody
public List<String> stats( @PathVariable int idEvent){
	 return cs.eventsStat(idEvent);
}
@GetMapping("/participants/export/pdf/{idEvent}")
public void export(HttpServletResponse response,@PathVariable int idEvent) throws DocumentException, IOException {
    response.setContentType("application/pdf");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());
     
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=events_" + currentDateTime + ".pdf";
    response.setHeader(headerKey, headerValue);
	String y = String.valueOf(idEvent);

    List<Eventparticipants> inv = cs.filee(y);
     
    Participantspdf exporter = new Participantspdf(inv);
    exporter.export(response);
     
}


@GetMapping("/filter/{EventTitle}")  
private List<Events> fil(@PathVariable("EventTitle") String EventTitle)   
{  
return cs.fil(EventTitle);  
}  
@GetMapping("/afficherlesparticipants/{idEv}") 
private List<Eventparticipants> filee(@PathVariable("idEv") String idEv)   
{  
return cs.filee(idEv);  
}  

@GetMapping("/filta/{idEv}/{iduser}") 
private List<Eventparticipants> filee(@PathVariable("idEv") String idEv,@PathVariable("iduser") String iduser)   
{  
return cs.fila(idEv,iduser);  
}  

@GetMapping("/findevents/{searchTerm}") 
private List<Events> findevents(@PathVariable("searchTerm") String abc)   
{  
return cs.find(abc);  
}  

//////////////////////////////////////////////////////////////////////News////////////////////////////////////////////////////////////






@GetMapping("/findnews/{searchTerm}") 
private List<News> findnews(@PathVariable("searchTerm") String abc)   
{  
return ns.find(abc);  
}  

@GetMapping("/afficherNew")  
private List<tn.esprit.spring.entities.News> getAllNew()   
{  
return ns.getallNew();  
}  

@PutMapping("/updateNew")  
private tn.esprit.spring.entities.News update(@RequestBody tn.esprit.spring.entities.News events )   
{  
ns.update( events);  
return events;  
}  

@GetMapping("/affNew/{News_id}")  
private News getNs(@PathVariable("News_id") int News_id)   
{  
	
ns.incrementViews(News_id);
return ns.getNsById(News_id);  

}  


@DeleteMapping("/deleteNew/{News_id}")  
private void deleteNew(@PathVariable("News_id") int News_id)   
{  
ns.delete(News_id);  

}  

@GetMapping("/rate/{News_id}/{a}")  
private float rate(@PathVariable("a") int a,  @PathVariable("News_id")int News_id)   
{  
return ns.rating(a, News_id);  
}  

@PostMapping("/ajouterNew")
@ResponseBody
public void ajouterNew(@RequestBody News d){
	ns.ajouterNew(d);
}
@PostMapping("/aff/{News_id}/{id}")
@ResponseBody
public void aff(  @PathVariable("News_id")int News_id ,@PathVariable("id") int id){
	ns.enregistrerdocumentdansnew(News_id,id);
}


    




@PostMapping("/send/{to}/{subject}/{content}")

public void send(@PathVariable("to") String to, @PathVariable("subject")String subject, @PathVariable("content")String content) {
ns.sendSimpleMail(to,subject,content);
   

}
















@GetMapping("/export/news")
public void exportToPDFNews (HttpServletResponse response) throws DocumentException, IOException {
    response.setContentType("application/pdf");
    
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=news_ .pdf";
    response.setHeader(headerKey, headerValue);
     
    List<News> inv = ns.getallNew();
     
    NewsPDFexporter exporter = new NewsPDFexporter(inv);
    exporter.export(response);
     
}






private StripeClient stripeClient;

@Autowired
Controller (StripeClient stripeClient) {
    this.stripeClient = stripeClient;
}



@Value("${stripe.apikey}")
String stripeKey;

@RequestMapping("/createCustomer")
public tn.esprit.spring.entities.CustomerData createCustomer(@RequestBody tn.esprit.spring.entities.CustomerData data) throws StripeException {
	Stripe.apiKey = stripeKey;
	Map<String, Object> params = new HashMap<>();
	params.put("name", data.getName());
	params.put("email", data.getEmail());
	params.put("amount", data.getAmount());
	params.put("currency", data.getCurrency());
	params.put("token", data.getStripeToken());
	Customer customer = Customer.create(params);
	data.setCustomerId(customer.getId());
	return data;
}

@PostMapping("/charge")
public Charge chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount) throws Exception {
    System.out.println(token);
    System.out.println(amount);
    return this.stripeClient.chargeNewCard(token, amount);

}















@Autowired
private FileStorageService storageService;
@PostMapping("/upload/{idnews}")
public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("idnews") String idnews) {
  String message = "";
  try {
    storageService.store(file,idnews);
    message = "Uploaded the file successfully: " + file.getOriginalFilename();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
  } catch (Exception e) {
    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
  }
}


@GetMapping("/files")
public ResponseEntity<List<ResponseFile>> getListFiles() {
  List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
    String fileDownloadUri = ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/files/")
        .path(String.valueOf(dbFile.getId()))
        .toUriString();
    return new ResponseFile(
        dbFile.getName(),
        fileDownloadUri,
        dbFile.getType(),
        dbFile.getData().length);
  }).collect(Collectors.toList());
  return ResponseEntity.status(HttpStatus.OK).body(files);
}
@GetMapping("/files/{id}")
public ResponseEntity<byte[]> getFile(@PathVariable int id) {
  FileDB fileDB = storageService.getFile(id);
  return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
      .body(fileDB.getData());
}
@GetMapping("/docnew/{id}") 
private List<FileDB> docnew(@PathVariable("id") String id)   
{  
return storageService.showdoc(id);  
}  



@Autowired
tn.esprit.spring.service.PaypalService service;
@PostMapping("/pay")
public String payment(@ModelAttribute("order") tn.esprit.spring.entities.Order order) {
	try {
		Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
				order.getIntent(), order.getDescription());
		for(Links link:payment.getLinks()) {
			if(link.getRel().equals("approval_url")) {
				return "redirect:"+link.getHref();
			}
		}
		
	} catch (PayPalRESTException e) {
	
		e.printStackTrace();
	}
	return "ok";
}





@GetMapping("/publishedbefore/{idnews}")  
private float publishedbefore(@PathVariable("idnews") int idnews)   
{  
return ns.JourRest (idnews);  
}  


@GetMapping("/fildact")  
private List<News> fildact()   
{  
return ns.filedactulites() ;  
}  

}








