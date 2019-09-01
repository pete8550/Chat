package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ServerController {

    Server server;

    @FXML
    TextArea chatText;
    @FXML
    TextArea chatWindowText;

    public ServerController() {

        server = new Server(this);
        (new Thread(server)).start();
    }

    public void onKeyReleased(KeyEvent keyEvent) {

        chatText.setWrapText(true);
        chatWindowText.setWrapText(true);

        if (keyEvent.getCode() == KeyCode.ENTER) {

            chatWindowText.appendText("Server: " + chatText.getText());
            server.send(chatText.getText());
            chatText.setText("");
        }
    }
}