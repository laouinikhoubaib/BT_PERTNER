package tn.esprit.spring.inteface;

import java.util.List;


import tn.esprit.spring.entities.Transport;

public interface ITransportService {
	public Transport addTransport( Transport T);
	public void deleteTransport(Integer id);
	public void updateTransport( Transport newTransport, int idTransport);
	public List<Transport> retrieveAllTransports();
	
}
