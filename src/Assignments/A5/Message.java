package Assignments.A5;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -8301715677091394408L;
	
	private String message;
	
	public Message(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
