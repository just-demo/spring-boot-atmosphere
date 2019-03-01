package self.ed.springbootatmosphere;

import org.atmosphere.config.service.*;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;

import static org.atmosphere.config.service.DeliverTo.DELIVER_TO.RESOURCE;
import static self.ed.springbootatmosphere.LogService.log;

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
