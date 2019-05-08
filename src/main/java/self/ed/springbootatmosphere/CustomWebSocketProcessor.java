package self.ed.springbootatmosphere;

import org.atmosphere.config.service.WebSocketProcessorService;
import org.atmosphere.websocket.DefaultWebSocketProcessor;
import org.atmosphere.websocket.WebSocket;

// https://github.com/Atmosphere/atmosphere/wiki/Understanding-Atmosphere-Annotations-for-Building-Application
// https://github.com/Atmosphere/atmosphere/wiki/Using-Atmosphere-Runtime-Annotations
@WebSocketProcessorService
public class CustomWebSocketProcessor extends DefaultWebSocketProcessor {
    public static final String CLOSE_CODE_ATTRIBUTE = CustomWebSocketProcessor.class.getSimpleName() + ".close.code";

    @Override
    public void executeClose(WebSocket webSocket, int closeCode) {
        webSocket.resource().getRequest().setAttribute(CLOSE_CODE_ATTRIBUTE, closeCode);
        super.executeClose(webSocket, closeCode);
    }
}
