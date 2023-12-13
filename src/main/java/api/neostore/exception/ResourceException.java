package api.neostore.exception;

import javax.ws.rs.core.Response.Status;

public class ResourceException extends RuntimeException {
	private Status httpStatus;
	public ResourceException() {
		super();
	}

	public ResourceException(Status httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public ResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public Status getHttpStatus() {
		return this.httpStatus;
	}
}