import java.io.*;
import java.net.*;

class Server
{
    public static void main(String args[])
    {
        try 
        {
            ServerSocket server = new ServerSocket(6920);
            System.out.println("Waiting for client...");
            while(true)
            {        
                Socket socket = server.accept();
                ClientThread client = new ClientThread(socket);
                client.start();
            }
            //server.close();
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
            //System.out.println("Error in Server : "+e.toString());
        }
    }
}