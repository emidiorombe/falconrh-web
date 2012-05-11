package richard.falconrh.exception;

import javax.ejb.ApplicationException;

/**
 *  Classe que mapea as exceções de negócio
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
@ApplicationException(rollback=true)
public class ServicesException extends Exception {
	private static final long serialVersionUID = -8711372472258173545L;

	public ServicesException() {
	}

	/**
	 * Constructor for ServicesException.
	 * @param message String
	 */
	public ServicesException(String message) {
		super(message);
	}

	/**
	 * Constructor for ServicesException.
	 * @param cause Throwable
	 */
	public ServicesException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor for ServicesException.
	 * @param message String
	 * @param cause Throwable
	 */
	public ServicesException(String message, Throwable cause) {
		super(message, cause);
	}

}
