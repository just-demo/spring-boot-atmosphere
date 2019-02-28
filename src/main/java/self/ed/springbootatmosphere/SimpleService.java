package self.ed.springbootatmosphere;

import org.atmosphere.config.service.*;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;

import static org.atmosphere.config.service.DeliverTo.DELIVER_TO.RESOURCE;

@ManagedService
public class SimpleService {
    @Ready
    public void onReady(AtmosphereResource resource) {
        System.out.println("Ready: " + resource);
    }

    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event) {
        System.out.println("Disconnect: " + event);
    }

    @Resume
    public void onResume(AtmosphereResourceEvent event) {
        System.out.println("Resume: " + event);
    }

    @Message
    @DeliverTo(RESOURCE)
    public void onMessage(AtmosphereResource resource, Object message) {
        System.out.println("Message: " + message);
    }
}
