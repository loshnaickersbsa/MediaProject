package vzap.losh.exceptions;
import java.util.Date;

public class UserClassException extends Exception {

	private String errorMessage;
	private Date date;

	public UserClassException(String errorMessage)
	{
		super();
		this.date = new Date();
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return "MediaNameException [message=" + errorMessage + "] " + this.date;
	}
		
}
