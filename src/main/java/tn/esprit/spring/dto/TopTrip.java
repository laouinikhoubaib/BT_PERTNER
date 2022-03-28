package tn.esprit.spring.dto;

public class TopTrip {

	public String destination;
	public int count;
	
	public TopTrip() {
	}
	
	public TopTrip(String destination, int count) {
		super();
		this.destination = destination;
		this.count = count;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
}
