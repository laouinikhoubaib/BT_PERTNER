package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Transport;
import tn.esprit.spring.inteface.ITransportService;

@RestController
@RequestMapping("/TransportController")
public class TransportController {
@Autowired
ITransportService TransportService;
@PostMapping("/AddTransport")
@ResponseBody
public Transport addTransport(@RequestBody Transport T) {
	return TransportService.addTransport(T);
	
}
@DeleteMapping("/deleteTransport/{Transport_id}")  
public void deleteTransport(@PathVariable("Transport_id")Integer id) {
	TransportService.deleteTransport(id);
}
@PutMapping("/updateTransport/{Transport-id}") 
public void updateTransport(@RequestBody Transport newTransport, @PathVariable("Transport-id")  int idTransport) {
	TransportService.updateTransport(newTransport, idTransport);
	
}
@GetMapping("/retrieveAllTransports")
public List<Transport> retrieveAllTransports(){
	return TransportService.retrieveAllTransports();
}
}
