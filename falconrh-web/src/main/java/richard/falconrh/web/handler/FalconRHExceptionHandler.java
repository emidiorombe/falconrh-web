package richard.falconrh.web.handler;
 
import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
 
/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class FalconRHExceptionHandler extends ExceptionHandlerWrapper {
 
    private ExceptionHandler wrapped;
 
    /**
     * Constructor for FalconRHExceptionHandler.
     * @param wrapped ExceptionHandler
     */
    public FalconRHExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }
 
    /**
     * Method getWrapped.
    
    
     * @return ExceptionHandler * @see javax.faces.FacesWrapper#getWrapped() */
    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }
 
    /**
     * Method handle.
    
     * @throws FacesException */
    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();
            if (t instanceof ViewExpiredException) {
                ViewExpiredException vee = (ViewExpiredException) t;
                FacesContext fc = FacesContext.getCurrentInstance();
                Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
                NavigationHandler nav = fc.getApplication().getNavigationHandler();
                try {
                    // Push some useful stuff to the request scope for use in the page
                    requestMap.put("currentViewId", vee.getViewId());
                    nav.handleNavigation(fc, null, "erroGeral");
                    fc.renderResponse();
                 } finally {
                    i.remove();
                }
            }else if(t instanceof Exception){
                FacesContext fc = FacesContext.getCurrentInstance();
                Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
                NavigationHandler nav = fc.getApplication().getNavigationHandler();
                FacesMessage facesMessage = new FacesMessage("Erro", t.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                try {
                    // Push some useful stuff to the request scope for use in the page
                    requestMap.put("currentViewId", t.getMessage());
                     nav.handleNavigation(fc, null, "erroGeral");
                    fc.renderResponse();
                 } finally {
                    i.remove();
                }
            }
        }
        // At this point, the queue will not contain any ViewExpiredEvents. Therefore, let the parent handle them.
        getWrapped().handle();
    }
}