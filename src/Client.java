import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 8000);//clients socket initialization
        Socket clientSocket2 = new Socket(InetAddress.getByName("186.148.0.12"),8000);
        Socket clientSocket3 = new Socket(InetAddress.getByName("127.0.0.1"),8000);

        BufferedReader reader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));//requires to server
        BufferedReader reader2 = new BufferedReader( new InputStreamReader(clientSocket2.getInputStream()));
        BufferedReader reader3 = new BufferedReader( new InputStreamReader(clientSocket3.getInputStream()));

        String msg = reader.readLine(); //prints the received data from the server
        System.out.println(msg);

        msg = reader2.readLine();
        System.out.println(msg);

        msg = reader3.readLine();
        System.out.println(msg);

        clientSocket.close();
        clientSocket2.close();
        clientSocket3.close();
    }
}