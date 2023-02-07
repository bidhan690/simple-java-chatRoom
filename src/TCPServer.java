import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Server ready for chatting");
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Accepted connection : " + connectionSocket);
            ClientHandler handler = new ClientHandler(connectionSocket);
            handler.start();
        }
    }
}

class ClientHandler extends Thread {
    private Socket connectionSocket;
    private DataInputStream inStream;
    private DataOutputStream outStream;
    private static ArrayList<ClientHandler> handlers = new ArrayList<>();
    private String name;

    ClientHandler(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            inStream = new DataInputStream(connectionSocket.getInputStream());
            outStream = new DataOutputStream(connectionSocket.getOutputStream());
            handlers.add(this);

            name = inStream.readUTF();
            System.out.println("Client name: " + name);
            sendMessage("Connected to the chat room");
            sendMessage("Welcome " + name + " to our chat room");
            broadcast(name + " joined the chat room");

            while (true) {
                String message = inStream.readUTF();
                System.out.println(name + ": " + message);
                broadcast(name + ": " + message);
            }
        } catch (EOFException e) {
            System.out.println(name + " disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            handlers.remove(this);
            broadcast(name + " left the chat room");
            try {
                connectionSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcast(String message) {
        for (ClientHandler handler : handlers) {
            handler.sendMessage(message);
        }
    }

    private void sendMessage(String message) {
        try {
            outStream.writeUTF(message);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
