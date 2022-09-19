import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class n2client {
    private static final String ser_ip="127.0.0.1";
    private static final int ser_port=9090;


    public static void main(String[] args)throws IOException {
        Socket s=new Socket(ser_ip,ser_port);
        BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader keyb=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(s.getOutputStream(),true);
        while(true){
            System.out.println(">");
            String comm=keyb.readLine();
            if(comm.equals("stop")) break;
            out.println(comm);
            String serverresponse=in.readLine();
            System.out.println("Server says "+serverresponse);
        }
        s.close();
    }
}
