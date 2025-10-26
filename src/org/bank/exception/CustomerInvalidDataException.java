package org.bank.exception;

public class CustomerInvalidDataException extends RuntimeException {
	private String exceptionMSG;
	
	public CustomerInvalidDataException() {
		
	}

	public CustomerInvalidDataException(String exceptionMSG) {
		this.exceptionMSG = exceptionMSG;
	}

	@Override
	public String toString() {
		return "CustomerInvalidDataException [exceptionMSG=" + exceptionMSG + "]";
	}

	public String getExceptionMSG() {
		return exceptionMSG;
	}

	public void setExceptionMSG(String exceptionMSG) {
		this.exceptionMSG = exceptionMSG;
	}
	
}
