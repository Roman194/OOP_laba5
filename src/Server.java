import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    public static void main(String[] args) throws IOException {

        ArrayList<InetAddress> clients = new ArrayList<>(); //list of all clients which is ridden from file
        BufferedReader bufferedReader = new BufferedReader(new FileReader("hosts.txt"));
        clients.add(InetAddress.getByName(bufferedReader.readLine()));
        clients.add(InetAddress.getByName(bufferedReader.readLine()));
        clients.add(InetAddress.getByName(bufferedReader.readLine()));
        bufferedReader.close();
        ArrayList<InetAddress> choosedClients = new ArrayList<>();
        //list of chosen clients by the admin to send a message on their next connection
        Scanner sc = new Scanner(System.in);

        System.out.println("Write down number of clients which should reach the message:\n");
        for(int i=0; i<clients.size(); i++){ //print of all possible hosts to send the message
            System.out.println(i+". "+clients.get(i)+"\n");
        }
        String [] list = sc.nextLine().split(" "); //massive with chosen clients indexes
        for(int i=0; i< list.length; i++){ //fill list of chosen clients by their hosts
            choosedClients.add(clients.get(Integer.parseInt(list[i])));
        }

        ServerSocket serverSocket = new ServerSocket(8000); //server socket initialization with number of default port

        for (int i=0;i<3;i++) {
            Socket clientSocket = serverSocket.accept(); //accepting connection with new client

            if(choosedClients.contains(clientSocket.getInetAddress())){ //sending to him the message if he was chosen by admin

                OutputStreamWriter wr = new OutputStreamWriter(clientSocket.getOutputStream());
                wr.write("Hola! You forgot about me? :/\n");
                wr.flush(); //this string is needed to make force send of the message if it wasn't still sent
                wr.close();
            }

            clientSocket.close();
        }
        serverSocket.close();

    }
}
