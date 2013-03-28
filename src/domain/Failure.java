package domain;

public class Failure {
	private String severity, message;
	
	public Failure(String severity, String message){
		this.severity = severity;
		this.message = message;
	}
	
	protected String getSeverity() {
		return severity;
	}

	protected void setSeverity(String severity) {
		this.severity = severity;
	}

	protected String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}
	

}
