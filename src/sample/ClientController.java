package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ClientController {

    Client client;

    @FXML
    TextArea chatTextArea;
    @FXML
    TextArea chatWindowTextArea;

    public ClientController() {
        client = new Client(this);
    }

    @FXML
    public void initialize() { (new Thread(client)).start(); }

    public void onKeyReleased (KeyEvent keyEvent) {

        chatTextArea.setWrapText(true);
        chatWindowTextArea.setWrapText(true);

        if (keyEvent.getCode() == KeyCode.ENTER) {

            chatWindowTextArea.appendText("Client: " + chatTextArea.getText());
            client.send(chatTextArea.getText());
            chatTextArea.setText("");
        }
    }
}
