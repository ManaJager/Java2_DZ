package ru.gb.dz.java2.kochemasov.lesson6.client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class ClientChat extends Application {

    public static final String NETWORK_ERROR_TITLE = "Connection error";
    public static final String NETWORK_ERROR_CONNECTION_TYPE = "Connection error";
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("chat.fxml")));
        Parent root = loader.load();

        primaryStage.setTitle("MyChat");
        primaryStage.setScene(new Scene(root));

        Controller controller = loader.getController();
        controller.userList.getItems().addAll("User1", "User2");

        this.primaryStage.show();

        connectToServer(controller);
    }

    private void connectToServer(Controller controller) {
        Network network = new Network();
        boolean result = network.connect();
        if (!result) {
            String msgErr = "Unable to connect with server!";
            showNetworkDialog(NETWORK_ERROR_CONNECTION_TYPE, msgErr);
            return;
        }
        controller.setNetwork(network);
        controller.setApplication(this);

        primaryStage.setOnCloseRequest(event -> network.close());
    }

    private void showErrorDialog(String title, String type, String details) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(type);
        alert.setContentText(details);
        alert.showAndWait();
    }

    public void showNetworkDialog(String type, String details) {
        showErrorDialog(NETWORK_ERROR_TITLE, type, details);
    }


    public static void main(String[] args) {
        launch(args);
    }
}