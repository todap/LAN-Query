import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Multiple extends Thread {
    Socket socket;
    BufferedReader reader;

    public Multiple(Socket socket) {
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String name = reader.readLine();
            System.out.println("New student joined: " + name);

            while (true) {
                String message = reader.readLine();
                if(message==null){
                    socket.close();
                    System.out.println(name+" is disconnected.");
                    break;
                }
                else
                System.out.println("Doubt from "+name+":"+message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}