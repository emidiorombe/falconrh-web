package richard.falconrh.web.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class FalconRHPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -1250253071496089271L;

	private static final Logger LOGGER = Logger.getLogger(FalconRHPhaseListener.class);
	/**
	 * Method afterPhase.
	 * @param event PhaseEvent
	
	 * @see javax.faces.event.PhaseListener#afterPhase(PhaseEvent) */
	@Override
	public void afterPhase(PhaseEvent event) {
		LOGGER.debug("Finalizando fase " + event.getPhaseId().toString());
		System.out.println("Finalizando fase " + event.getPhaseId().toString());
	}

	/**
	 * Method beforePhase.
	 * @param event PhaseEvent
	
	 * @see javax.faces.event.PhaseListener#beforePhase(PhaseEvent) */
	@Override
	public void beforePhase(PhaseEvent event) {
		LOGGER.debug("Iniciando fase " + event.getPhaseId().toString());
		System.out.println("Iniciando fase " + event.getPhaseId().toString());
	}

	/**
	 * Method getPhaseId.
	
	
	 * @return PhaseId * @see javax.faces.event.PhaseListener#getPhaseId() */
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
