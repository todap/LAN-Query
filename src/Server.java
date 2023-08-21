import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    final static int port = 444;
    ArrayList<Multiple> multiple;
    public Server() {
        multiple =  new ArrayList<Multiple>();
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            InetAddress ip=InetAddress.getLocalHost();
            System.out.println("***Server Started***\nListening on Port: " + port+"\nIP Address: "+ip.getHostAddress());
            while (true) {
                socket = serverSocket.accept();
                Multiple student = new Multiple(socket);
                multiple.add(student);
                student.start();
            }
        } catch (Exception e) {
            System.out.println("Failed to create Socket" + e.getMessage());

        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.start(port);

    }
}
