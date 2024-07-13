//Imports needed for WavernTCP, do not remove.
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    //Classes
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter((socket.getOutputStream())));
            this.username = username;
        } catch (IOException e) {
            // If an error occurred, the server will "closeEverything" in this order:
            // Reader (Displays messages), Writer (Sends), socket (server)
            closeEverything(bufferedReader, bufferedWriter, socket);
            e.printStackTrace();
        }
    }
// If a user sends a message
    public void sendMessage() {
    try {
        // Put the username before the message
        bufferedWriter.write(username);
        // Add a new line, so the user can type another line.
        bufferedWriter.newLine();
        // Refresh everything.
        bufferedWriter.flush();
        // Scans for new messages
        Scanner scanner = new Scanner(System.in);
        while (socket.isConnected()) {
            String messageToSend = scanner.nextLine();
            // Puts "usernamehere:" before the message
            bufferedWriter.write(username + ": " + messageToSend);
            // New line
            bufferedWriter.newLine();
            System.out.println("Message sent!");
            // Refresh
            bufferedWriter.flush();
        }
        // If an error occurred, the server will "closeEverything" in this order:
    } catch (IOException e) {
        closeEverything(bufferedReader, bufferedWriter, socket);
    }
    }
    // Looks for new messages
    public void listenForMessage() {
        new Thread(new Runnable() {
            public void run() {
            String msgFromGroupChat;

            while (socket.isConnected()) {
                try {
                    // Reads message
                    msgFromGroupChat = bufferedReader.readLine();
                    // Prints it so everyone can see the message.
                    System.out.println(msgFromGroupChat);

                    // If an error occurred, the server will "closeEverything" in this order:
                } catch (IOException e) {
                     closeEverything(bufferedReader, bufferedWriter, socket);
                    System.out.println("Disconnected due to error.");
                }
            }
            }
        }).start();
    }
    // Reads messages
    public void closeEverything(BufferedReader bufferedReader,BufferedWriter bufferedWriter,Socket socket) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Disconnected due to error.");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        // Login system
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("SERVER: You have connected to the server successfully!");
        // Joins the server
        // NOTE: IF YOU WANT TO JOIN SOMEONE ELSE'S SERVER, ASK THEM FOR THE SERVER IP AND PORT. REPLACE "localhost"
        // AND "1234" WITH THE SERVER IP AND PORT GIVEN TO YOU
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username);
        // If a user presses enter on a message, it sends it.
        client.listenForMessage();
        client.sendMessage();
    }

}
