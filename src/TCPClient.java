import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = input.nextLine();
        Socket clientSocket = new Socket("localhost", 6789);
        System.out.println("Connected to the chat room");
        DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
        outStream.writeUTF(name);
        outStream.flush();

        ChatClientThread chatClientThread = new ChatClientThread(inStream);
        chatClientThread.start();

        while (true) {
            String message = input.nextLine();
            outStream.writeUTF(message);
            outStream.flush();
        }
    }
}

class ChatClientThread extends Thread {
    private DataInputStream inStream;

    ChatClientThread(DataInputStream inStream) {
        this.inStream = inStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = inStream.readUTF();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
