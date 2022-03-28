package tn.esprit.spring.controller;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.lowagie.text.DocumentException;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import io.swagger.models.Model;
import io.swagger.v3.oas.models.media.MediaType;
import lombok.var;
import nonapi.io.github.classgraph.utils.StringUtils;
import tn.esprit.spring.dto.TopTrip;
import tn.esprit.spring.entities.Complaint;
import tn.esprit.spring.entities.Payment;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.entities.Visa;
import tn.esprit.spring.inteface.IComplaintService;
import tn.esprit.spring.inteface.IReservationService;
import tn.esprit.spring.inteface.ITripService;
import tn.esprit.spring.repository.VisaRepository;
import tn.esprit.spring.service.UploadFile;
import tn.esprit.spring.service.UserPDFExporter;




@RestController
@RequestMapping("/TripController")
public class TripController {
	@Autowired
	ITripService TripService;
	@Autowired
	IComplaintService ComplaintService;
	@Autowired
	IReservationService reservationService;
	@Autowired 
     VisaRepository Vrepo;
	@Autowired
	UploadFile uploadService;
	@PostMapping("/AddTrip")
	@ResponseBody
	public Trip ajouterClasse(@RequestBody Trip T) {
	return  TripService.addTrip(T);
	}
	
	@DeleteMapping("/deleteTrip/{Trip_id}")  
	public void deleteTrip(@PathVariable("Trip_id")Integer id) {
		TripService.deleteTrip(id);
	}
	@PutMapping("/updateTrip/{trip-id}")  
	public void updateTrip(@RequestBody Trip newtrip, @PathVariable ("trip-id") int idTrip) {
		TripService.updateTrip(newtrip, idTrip);
	}
	@GetMapping("/retrieveAllTrips")
	public List<Trip> retrieveAllTrips(){
		return TripService.retrieveAllTrips();
	}
	@GetMapping("/retrieveAllTripsByDestination/{Destination}")
	public List<Trip> ShowListOfTripsByDestination(@PathVariable ("Destination") String TripDestination){
		return TripService.ShowListOfTripsByDestination(TripDestination);
	}
	@GetMapping("/SearchTripByDate/{x}/{y}/{z}")
	@ResponseBody
	public List<Trip> SearchTrips(@PathVariable("x")@DateTimeFormat(pattern="yyyy-MM-dd")Date d1 ,@PathVariable("y")@DateTimeFormat(pattern="yyyy-MM-dd")Date d2,@PathVariable("z")String name){
		return TripService.SearchTrips(d1, d2, name);
	}
	@GetMapping("/GetNumberOfTripsByDate(/{x}/{y}")
	@ResponseBody
	public int GetNumberOfTripsByDate(@PathVariable("x")@DateTimeFormat(pattern="yyyy-MM-dd")Date d1, @PathVariable("y")@DateTimeFormat(pattern="yyyy-MM-dd")Date d2) {
		return TripService.GetNumberOfTripsByDate(d1, d2);
	}
	@GetMapping("/GetListOfTrips(/{x}/{y}")
	@ResponseBody
	public List<Trip> ListOfTrips(){
		return TripService.ListOfTrips();
		
	}
	@GetMapping("/numberOfTripsByDestination/{Destination}")
	public int numberOfTripsByDestination(@PathVariable ("Destination") String TripDestination) {
		return TripService.numberOfTripsByDestination(TripDestination);
	}
	@GetMapping("/TopTripsByUser")
	public int TopTripsByUser() {
		return TripService.TopTripsByUser();
		
	}
	
	@GetMapping("/TripRevenu/{id}")
	@ResponseBody
	public Integer Revenu(@PathVariable("id")Integer idTrip)
	{
		return TripService.getRevenuByTrip(idTrip);
	}
	@PutMapping("/affecterPartnertoTrip/{idPartner}/{idTrip}")
	@ResponseBody
	@Transactional
	public void affecterPartnertoTrip(@PathVariable("idPartner")Integer iduser ,@PathVariable("idTrip") Integer idTrip)
	{
		TripService.affecterPartnertoTrip(iduser, idTrip);
	}
	@GetMapping("/ RetrieveAllTripsOfOneByUser/{id-user}")
	public List <Trip> RetrieveAllTripsOfOneByUser(@PathVariable("id-user")Integer iduser){
		return TripService.RetrieveAllTripsOfOneByUser(iduser);
	}

	@GetMapping("/GetTripByObject/{object}")
	public int GetTripByObject( @PathVariable("object") String tripObject) {
		 return TripService.GetTripByObject(tripObject);
	}
	/*@GetMapping("/Top3TripsByDestination")
	public List<TopTrip> TopTripByDestination() {
		return TripService.TopTripByDestination();
	}*/
	


	@GetMapping("/Trips/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Trips_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<Trip> listTrips = TripService.retrieveAllTrips();  
        UserPDFExporter exporter = new UserPDFExporter(listTrips);
        exporter.export(response);
         
    }
	@GetMapping("/Trips/list/{idUser}")
	@ResponseBody
	public List<Trip> List(@PathVariable("idUser")Integer idUser){
		return TripService.List(idUser);
	}
	@GetMapping("/Trips/listpay/{idUser}")
	public List<Payment> listPaymentByUser(@PathVariable("idUser")Integer iduser){
		return TripService.listPaymentByUser(iduser);
	}
	@PostMapping("/AddPayment")
	@ResponseBody
	public Payment addPayment(Payment p) {
		return TripService.addPayment(p);
	}
	@GetMapping("/Trips/RetrieveAllPayments")
	public List<Payment> RetrieveAllPayments() {
		return TripService.RetrieveAllPayments();
	}
	/*@GetMapping("/Paiement/export/pdf")
    public void exportToPDF1(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Trips_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        //List<Trip> listTrips = TripService.retrieveAllTrips();  
        //float price =reservationService.TotalReservation(1);
        float price =12;
       UserPDFExporter exporter = new UserPDFExporter(price);
        System.err.println("price="+price);
       exporter.export(response);
         
    }*/

	@PostMapping("/uploadimage/{idVisa}")
	  public Visa uploadFile(@RequestParam("file") MultipartFile uploadFile,@PathVariable("idVisa")Integer idVisa) throws IllegalStateException, IOException {
	   try {
	    Visa v =TripService.addVisa(uploadFile,idVisa);
	 
	    uploadFile.transferTo(new File("D:\\UploadFiles"+uploadFile.getOriginalFilename()));
	      
	    
	    } catch (Exception e) {
	      String message = "Could not upload the file: " + uploadFile.getOriginalFilename() + "!";
	     
	    }
	    return TripService.addVisa(uploadFile,idVisa);
	  }
	@PutMapping("/updateUnconfirmedVisa/{Visa-id}")
	@Transactional
	public Visa UpdateVisa(@PathVariable(value ="Visa-id")Integer id) {
		return TripService.UpdateVisa(id);
	}
	@PostMapping("/visa/save")
    public void saveUser(
            @RequestParam("image") MultipartFile file) throws IOException {
         
       /*String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //visa.setPhotos(fileName);
         
        //Visa savedVisa = Vrepo.save(visa);
 
        String uploadDir = "user-photos/" + savedVisa.getVisa_id();
 
        uploadService.saveFile(uploadDir, fileName, multipartFile);
         
        return new RedirectView("/users", true);*/
		//uploadService.saveFile(file);
    }
}