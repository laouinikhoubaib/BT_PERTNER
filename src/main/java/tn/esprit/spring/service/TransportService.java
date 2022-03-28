package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Reservation;
import tn.esprit.spring.entities.Transport;
import tn.esprit.spring.inteface.ITransportService;
import tn.esprit.spring.repository.ReservationRepository;
import tn.esprit.spring.repository.TransportRepository;

@Service
public class TransportService implements ITransportService {
@Autowired
TransportRepository Trepo;
@Autowired
ReservationRepository Rrepo;

@Override
public Transport addTransport(Transport T) {
	// TODO Auto-generated method stub
	Transport transport  = Trepo.save(T);
	return transport;
}

@Override
public void deleteTransport(Integer id) {
	// TODO Auto-generated method stub
	Trepo.deleteById(id);
	
}

@Override
public void updateTransport(Transport newTransport, int idTransport) {
	// TODO Auto-generated method stub
	//Complaint c = Crepo.findById(idComplaint).orElse(null);
	Transport T = Trepo.findById(idTransport).orElse(null);
	T.setTransportmeans(newTransport.getTransportmeans());
	T.setTransportPrice(newTransport.getTransportPrice());
	Trepo.save(T);
}

@Override
public List<Transport> retrieveAllTransports() {
	// TODO Auto-generated method stub
	return (List<Transport>) Trepo.findAll();
}



}
