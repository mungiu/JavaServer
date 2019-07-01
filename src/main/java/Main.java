import Controller.SocketServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            new SocketServer().startServer();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
