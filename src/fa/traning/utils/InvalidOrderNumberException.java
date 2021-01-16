package fa.traning.utils;

public class InvalidOrderNumberException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidOrderNumberException() {
		super();
	}

	public InvalidOrderNumberException(String message) {
		super(message);
	}

}
