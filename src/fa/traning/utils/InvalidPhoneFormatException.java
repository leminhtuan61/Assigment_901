package fa.traning.utils;

public class InvalidPhoneFormatException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidPhoneFormatException() {
		super();
	}

	public InvalidPhoneFormatException(String message) {
		super(message);
	}
}
