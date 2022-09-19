
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


 class clienthandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public clienthandler(Socket clientsocket) throws IOException {
        this.client = clientsocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
    }
//run() is a abstact method

    @Override
    public void run() {
        try {
            while (true){
                String request=in.readLine();
                if(request.contains("name")){
                    out.println("rkbansal");
                }
                else{
                    out.println("hello i am the server you can ask me m name");

                }
            }
        }
        catch(IOException e){
            System.err.println(e.getStackTrace());
        }
        finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }






public class bigserver {
    private static final int port=9090;
    private static ArrayList<clienthandler> clients=new ArrayList<>();
    private static ExecutorService pool= Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws IOException {
        ServerSocket list = new ServerSocket(port);
 while(true) {
            System.out.println("waiting");
            Socket client = list.accept();
            System.out.println("server connected to client");
            clienthandler clientthread =new clienthandler(client);
            clients.add(clientthread);
            pool.execute(clientthread);
        }

    }
}
