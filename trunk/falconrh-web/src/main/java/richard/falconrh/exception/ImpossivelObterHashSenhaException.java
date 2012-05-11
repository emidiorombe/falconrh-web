package richard.falconrh.exception;

/**
 * Classe para mapeamento de Exceções que são disparadas quando for impossível transformar uma senha plana em Hash Base64
 * @author Richard Mendes Madureira
 *
 * @version $Revision: 1.0 $
 */
public class ImpossivelObterHashSenhaException extends RuntimeException {
	private static final long serialVersionUID = -6128969936368310794L;

	public ImpossivelObterHashSenhaException() {
		super();
	}

	/**
	 * Constructor for ImpossivelObterHashSenhaException.
	 * @param message String
	 * @param cause Throwable
	 */
	public ImpossivelObterHashSenhaException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for ImpossivelObterHashSenhaException.
	 * @param message String
	 */
	public ImpossivelObterHashSenhaException(String message) {
		super(message);
	}

	/**
	 * Constructor for ImpossivelObterHashSenhaException.
	 * @param cause Throwable
	 */
	public ImpossivelObterHashSenhaException(Throwable cause) {
		super(cause);
	}

}
