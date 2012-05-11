package richard.falconrh.web.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class FalconRHExceptionHandlerFactory extends ExceptionHandlerFactory {
	private ExceptionHandlerFactory parent;

	/**
	 * Constructor for FalconRHExceptionHandlerFactory.
	 * @param parent ExceptionHandlerFactory
	 */
	public FalconRHExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	/**
	 * Method getExceptionHandler.
	
	 * @return ExceptionHandler */
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = parent.getExceptionHandler();
		result = new FalconRHExceptionHandler(result);
		return result;
	}
}