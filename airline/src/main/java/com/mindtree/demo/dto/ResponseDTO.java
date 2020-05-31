package com.mindtree.demo.dto;

public class ResponseDTO {

	private String message;

	private boolean success;

	private boolean error;

	private Object body;


	public ResponseDTO() {
		super();
	}

	public ResponseDTO(String message, boolean success, boolean error, Object body) {
		super();
		this.message = message;
		this.success = success;
		this.error = error;
		this.body = body;
	}

	public ResponseDTO(String message, Throwable cause) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
