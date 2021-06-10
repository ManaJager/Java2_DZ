package ru.gb.dz.java2.kochemasov.l6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        new Client().startClientConnection(ConnectionSettings.SERVER_HOST, ConnectionSettings.SERVER_PORT);
    }

    public void startClientConnection(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("Client started and connected to server.");
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Conversations.receiveMessages(input, "server").start();
            Conversations.sendMessage(output, "client");
        } catch (IOException e) {
            System.err.println("Failed to stable connection. Server must start first!");
        }
    }
}
