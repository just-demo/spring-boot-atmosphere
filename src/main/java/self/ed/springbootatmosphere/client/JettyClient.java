package self.ed.springbootatmosphere.client;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;

import static org.eclipse.jetty.websocket.api.StatusCode.BAD_DATA;

public class JettyClient {
    private static WebSocketClient client;
    private static Session session;

    public static void main(String[] args) throws Exception {
        connect("ws://localhost:8080/test");
        System.out.println("Disconnecting...");
        disconnect();
        System.out.println("Disconnected!");
    }

    private static void disconnect() throws Exception {
        // TODO: why is this not working?
        session.close(BAD_DATA, "Demo message");
        client.stop();
    }

    private static void connect(String url) throws Exception {
        client = new WebSocketClient();
        client.start();
        URI uri = new URI(url);
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        client.connect(new Listener(), uri, request).get();
    }

    private static void send(Object message) {
        try {
            System.out.println("sending: " + message);
            session.getRemote().sendString(message.toString());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static class Listener implements WebSocketListener {
        @Override
        public void onWebSocketBinary(byte[] payload, int offset, int len) {

        }

        @Override
        public void onWebSocketText(String message) {
            System.out.println("message: " + message);
        }

        @Override
        public void onWebSocketClose(int statusCode, String reason) {
            System.out.println("close: " + statusCode + " " + reason);
        }

        @Override
        public void onWebSocketConnect(Session session) {
            JettyClient.session = session;
            System.out.println("open : " + session.getUpgradeResponse());
            send("open");
        }

        @Override
        public void onWebSocketError(Throwable cause) {

        }
    }
}
