package ru.gb.dz.java2.kochemasov.l6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        new Server().startServerConnection(ConnectionSettings.SERVER_PORT);
    }

    private void startServerConnection(int port) {
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server has been started, waiting for client connection...");
            Socket clientSocket = socket.accept();
            System.out.println("Client just connected.");
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            Conversations.receiveMessages(input, "client").start();
            Conversations.sendMessage(output, "server");
        } catch (IOException e) {
            System.err.println("Failed to stable connection. Check connection credentials!");
        }
    }
}
