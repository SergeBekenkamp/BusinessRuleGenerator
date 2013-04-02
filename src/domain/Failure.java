package domain;

public class Failure {
	private String severity, message;

	public Failure(String severity, String message) {
		this.severity = severity;
		this.message = message;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
