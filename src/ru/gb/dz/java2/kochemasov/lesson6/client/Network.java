package ru.gb.dz.java2.kochemasov.lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class Network {
    private static final int SERVER_PORT = 8189;
    private static final String SERVER_HOST = "localhost";

    private final String host;
    private final int port;
    private Socket socket;
    private DataInputStream socketInput;
    private DataOutputStream socketOutput;



    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Network() {
        this(SERVER_HOST, SERVER_PORT);
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            socketInput = new DataInputStream(socket.getInputStream());
            socketOutput = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            System.err.println("Failed to establish connection!");
            return false;
        }
    }

    public void sendMessage(String message) throws IOException {
        try {
            socketOutput.writeUTF(message);
        } catch (IOException e) {
            System.err.println("Failed to send message!");
            throw e;
        }
    }

    public void waitMessages(Consumer<String> messageHandler) {
        Thread thread = new Thread(()-> {
            while (true) {
                try {
                    String message = socketInput.readUTF();
                    messageHandler.accept(message);
                } catch (IOException e) {
                    System.err.println("Failed to read message from server!");
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
