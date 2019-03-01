package self.ed.springbootatmosphere;

import org.atmosphere.wasync.*;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.atmosphere.wasync.Event.*;
import static org.atmosphere.wasync.Request.METHOD.GET;
import static org.atmosphere.wasync.Request.TRANSPORT.WEBSOCKET;

public class AtmosphereClient {
    private static Socket socket;
    public static void main(String[] args) throws IOException, InterruptedException {
        connect("ws://localhost:8080/test");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(3000);
            send(i);
        }
    }

    private static void connect(String url) throws IOException {
        Client client = ClientFactory.getDefault().newClient();
        Options options = client.newOptionsBuilder()
                .reconnect(true)
                .pauseBeforeReconnectInSeconds(1)
                .reconnectAttempts(100)
                .requestTimeoutInSeconds(60)
                .build();

        Request request = client.newRequestBuilder()
                .uri(url)
                .method(GET)
                .transport(WEBSOCKET)
                .build();

        socket = client.create(options)
                .on(OPEN, message -> {
                    System.out.println("open : " + message);
                    send("open");
                })
                .on(REOPENED, message -> {
                    System.out.println("reopened: " + message);
                    send("reopened");
                })
                .on(MESSAGE, message -> System.out.println("message: " + message))
                .on(CLOSE, message -> System.out.println("close: " + message));

        socket.open(request);
    }

    private static void send(Object message) {
        try {
            System.out.println("sending: " + message);
            socket.fire(message.toString());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}