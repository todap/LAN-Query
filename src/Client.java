
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client  {
    final int port;
    String text;
    public Client(int port){
        this.port=port; 
    }
    public void start(int port,InetAddress addr) {
        try{
            Socket socket = new Socket(addr,port);
            System.out.println("***Connected to Server***\nPort Number:"+port+"\nIP Address:"+addr);
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output=new PrintWriter(socket.getOutputStream(),true);
            System.out.print("Enter your name:");
            String studentName=reader.readLine();
            output.println(studentName);
            while(true){
                System.out.print("Enter text:");
                String text= reader.readLine();
                if(text==null){
                    output.println(text);
                    socket.close();
                    break;
                }
                else{
                    output.println(text);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Failed to connect with server:"+e.getMessage());
        }
       
        
    }
    public static void main(String[] args)throws Exception {
        System.out.print("Enter Port Number:");
        try (Scanner sc = new Scanner(System.in)) {
            int port = sc.nextInt();
            System.out.print("Enter IP Address:");
            String hostIn=sc.next();
            InetAddress addr=InetAddress.getByName(hostIn);
            Client c= new Client(port);
            c.start(port,addr);
        }
    }
}
