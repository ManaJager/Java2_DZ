package ru.gb.dz.java2.kochemasov.lesson6.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class Controller {
    @FXML
    public ListView<String> userList;
    @FXML
    private TextField textInp;
    @FXML
    private Button btnSend;
    @FXML
    private TextArea chatArea;

    private Network network;
    private ClientChat application;

    @FXML
    private void sendAction(ActionEvent actionEvent) {
        sendMessage();
    }

    @FXML
    private void onKeyPressedInInputInTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            chatArea.appendText(System.lineSeparator());
            sendMessage();
        }
    }

    private void sendMessage() {
        String message = textInp.getText().trim();
        if (message.isEmpty()) {
            textInp.clear();
            return;
        }

        String sender = null;
        if (!userList.getSelectionModel().isEmpty()) {
            sender = userList.getSelectionModel().getSelectedItem();
        }

        try {
            message = sender != null ? String.join(": ", sender, message) : message;
            network.sendMessage(message);
        } catch (IOException e) {
            application.showNetworkDialog("Ошибка передачи данных по сети", "Не удалось отправить сообщение");
        }
        appendMessageToChat("Me", message);
    }

    private void appendMessageToChat(String sender, String message) {
        chatArea.appendText(DateFormat.getDateTimeInstance().format(new Date()));
        chatArea.appendText(System.lineSeparator());
        if (sender != null) {
            chatArea.appendText(sender + ":");
            chatArea.appendText(System.lineSeparator());
        }
        chatArea.appendText(message);
        chatArea.appendText(System.lineSeparator());
        chatArea.appendText(System.lineSeparator());
        textInp.clear();
    }

    public void setNetwork(Network network) {
        this.network = network;
        network.waitMessages(message -> Platform.runLater(() -> {
                Controller.this.appendMessageToChat("Server", message);
            }));
    }

    public void setApplication(ClientChat application) {
        this.application = application;
    }
}