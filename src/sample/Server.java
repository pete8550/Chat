package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    ServerController controller;
    DataInputStream in;
    DataOutputStream out;

    public Server(ServerController controller) {
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
            ServerSocket serverSocket = new ServerSocket(8000);
            Socket socket = serverSocket.accept();
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            while (true) {
                controller.chatWindowText.setWrapText(true);
                controller.chatWindowText.appendText("Client: " + in.readUTF());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
