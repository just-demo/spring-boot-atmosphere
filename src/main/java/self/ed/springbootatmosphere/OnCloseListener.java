package self.ed.springbootatmosphere;

import org.atmosphere.websocket.WebSocketEventListenerAdapter;

import static self.ed.springbootatmosphere.LogService.log;

public class OnCloseListener extends WebSocketEventListenerAdapter {
    @Override
    public void onClose(WebSocketEvent event) {
        log("close code: " + event.message());
    }
}
