package ru.gb.dz.java2.kochemasov.l6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Conversations {
    public static void sendMessage(DataOutputStream output, String autor){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String outputMessage = scanner.next();
            try {
                output.writeUTF(outputMessage);
                System.out.println("Message from " + autor + " at " +
                        DateFormat.getDateTimeInstance().format(new Date()) +
                        ": " + outputMessage);
            } catch (IOException e) {
                System.err.println("Failed to send message!");
            }
            if (outputMessage.equals("/end")) {
                break;
            }
        }
    }

    public static Thread receiveMessages(DataInputStream input, String autor) {
        return  new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String inputMessage = input.readUTF();
                    System.out.println("Message from " + autor + " at " +
                            DateFormat.getDateTimeInstance().format(new Date()) +
                            ": " + inputMessage);
                    if (inputMessage.equals("/end")) {
                        System.exit(0);
                    }
                } catch (IOException e) {
                    System.out.println("Failed to connect");
                    break;
                }
            }
        });
    }
}
