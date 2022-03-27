package tn.esprit.spring.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
	
	
	  private String message;

	  public MessageResponse(String message) {
	    this.message = message;
	  }

	  
	  
	  
	  
	  @Getter
	  @Setter	 
	 public class Response {

    private int statusCode;
    private String statusMessage;


    public Response(int statusCode, String message) {
        super();
        this.statusCode=statusCode;
        this.statusMessage=message;
    }
    public Response() {
        super();
    }



}
 
	}