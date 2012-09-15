package richard.falconrh.web.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;

/**
 * Classe que monitora o ciclo de vida do JavaServer Faces
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
public class FalconRHPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -1250253071496089271L;

	private static final Logger LOGGER = Logger.getLogger(FalconRHPhaseListener.class);
	
	/**
	 * Método que é executado após uma fase
	 * @param event PhaseEvent
	 * @see javax.faces.event.PhaseListener#afterPhase(PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent event) {
		LOGGER.debug("Finalizando fase " + event.getPhaseId().toString());
	}

	/**
	 * Método que é executado ante da fase
	 * @param event PhaseEvent
	 * @see javax.faces.event.PhaseListener#beforePhase(PhaseEvent)
	 */
	@Override
	public void beforePhase(PhaseEvent event) {
		LOGGER.debug("Iniciando fase " + event.getPhaseId().toString());
	}

	/**
	 * Método que retorna a fase que está sendo atualmente executada.
	 * @return PhaseId * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
