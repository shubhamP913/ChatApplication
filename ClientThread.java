import java.io.*;
import java.net.*;

public class ClientThread extends Thread
{
    private Socket socket = null;
    public  ClientThread(Socket socket)
    {
        this.socket = socket;
    }
    public void run()
    {
        try 
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Hello Client");
            String message;
            do
            {
                message = in.readLine();
                System.out.println(message);
            }
            while(message.compareTo("BYE")!=0);
            in.close();
            out.close();
            socket.close();
            
        } 
        catch (Exception e) 
        {
            System.out.println("Error in ClientThread : "+e.toString());
        }
    }
    
}