package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    ClientController controller;

    public Client(ClientController controller) {
        this.controller = controller;
    }

    public void send(String msg) {

        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            socket = new Socket("localhost", 8000);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                controller.chatWindowTextArea.setWrapText(true);
                controller.chatWindowTextArea.appendText("Server: " + in.readUTF());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
