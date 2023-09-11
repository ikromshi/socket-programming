import java.io.*;
import java.net.*;

public class TCPServer {
        public static void main(String argv[]) throws Exception {
                String clientSentence;
                StringBuilder entireMessageFromClient = new StringBuilder();
                ServerSocket welcomeSocket = new ServerSocket(6789);

                while (true) {
                        Socket connectionSocket = welcomeSocket.accept();
                        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                        while (!(clientSentence = inFromClient.readLine()).equals("END_OF_MESSAGE")) {
                                entireMessageFromClient.append(clientSentence).append("\n");
                        }

                        System.out.println("Message from client: " + entireMessageFromClient.toString());
                        String capitalizedMessage = entireMessageFromClient.toString().toUpperCase();
                        outToClient.writeBytes(capitalizedMessage + "END_OF_MESSAGE\n");

                        // Clear the StringBuilder for the next message
                        entireMessageFromClient.setLength(0);
                }
        }
}

