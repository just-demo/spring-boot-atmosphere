package self.ed.springbootatmosphere;

import org.atmosphere.config.service.*;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;

import static org.atmosphere.config.service.DeliverTo.DELIVER_TO.RESOURCE;
import static self.ed.springbootatmosphere.CustomWebSocketProcessor.CLOSE_CODE_ATTRIBUTE;
import static self.ed.springbootatmosphere.LogService.log;

/**
 * @see org.atmosphere.websocket.DefaultWebSocketProcessor#executeClose
 * TODO: the listener does not work because of AsynchronousProcessor#completeLifecycle,
 *       which calls AtmosphereResourceImpl#_destroy() and AtmosphereResourceImpl#removeEventListeners()
 */
//@ManagedService(listeners = OnCloseListener.class)
@ManagedService
public class AtmosphereService {
    @Ready
    public void onReady(AtmosphereResource resource) {
        log("ready: " + resource);
        resource.write("ready");
    }

    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event) {
        log("disconnect: " + event);
        log("close code: " + event.getResource().getRequest().getAttribute(CLOSE_CODE_ATTRIBUTE));
    }

    @Resume
    public void onResume(AtmosphereResourceEvent event) {
        log("resume: " + event);
    }

    @Message
    @DeliverTo(RESOURCE)
    public void onMessage(AtmosphereResource resource, Object message) {
        log("message: " + message);
        resource.write("response to " + message);
    }
}
