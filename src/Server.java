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

        ArrayList<InetAddress> clients = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("hosts.txt"));
        clients.add(InetAddress.getByName(bufferedReader.readLine()));
        clients.add(InetAddress.getByName(bufferedReader.readLine()));
        clients.add(InetAddress.getByName(bufferedReader.readLine()));
        bufferedReader.close();
        ArrayList<InetAddress> choosedClients = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Write down number of clients which should reach the message:\n");
        for(int i=0; i<clients.size(); i++){
            System.out.println(i+". "+clients.get(i)+"\n");
        }
        String [] list = sc.nextLine().split(" ");
        for(int i=0; i< list.length; i++){
            choosedClients.add(clients.get(Integer.parseInt(list[i])));
        }

        ServerSocket serverSocket = new ServerSocket(8000);

        for (int i=0;i<3;i++) {
            Socket clientSocket = serverSocket.accept();

            if(choosedClients.contains(clientSocket.getInetAddress())){

                OutputStreamWriter wr = new OutputStreamWriter(clientSocket.getOutputStream());
                wr.write("Hola! You forgot about me? :/\n");
                wr.flush();
                wr.close();
            }

            clientSocket.close();
        }
        serverSocket.close();

    }
}
