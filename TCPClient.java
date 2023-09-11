import java.io.*;
import java.net.*;

public class TCPClient {
        public static void main(String argv[]) throws Exception {
                String sentence;
                StringBuilder entireMessage = new StringBuilder();
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter your input or EOF to finish: ");
                while (!(sentence = inFromUser.readLine()).equals("EOF")) {
                        entireMessage.append(sentence).append("\n");
                }
                entireMessage.append("END_OF_MESSAGE\n");  // append delimiter

                Socket clientSocket = new Socket("localhost", 6789);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                outToServer.writeBytes(entireMessage.toString());

                StringBuilder responseFromServer = new StringBuilder();
                String responseLine;
                while (!(responseLine = inFromServer.readLine()).equals("END_OF_MESSAGE")) {
                        responseFromServer.append(responseLine).append("\n");
                }

                System.out.println("From Server: " + responseFromServer.toString());

                clientSocket.close();
        }
}

