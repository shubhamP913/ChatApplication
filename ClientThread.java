import java.util.*;

import java.io.*;
import java.net.*;

class ClientThread extends Thread
{
    private Socket socket;
    private Server server;
    private PrintWriter writer;
    public  ClientThread(Socket socket,Server server)
    {
        this.socket = socket;
        this.server = server;
    }
    public void run()
    {
        try 
        {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String userName = reader.readLine();

            String serverMessage = userName+" connected.";
            writer.println(serverMessage);
            String clientMessage;
            do
            {
                clientMessage = reader.readLine();
                server.broadcast("["+userName+"] :"+clientMessage,this);
            }
            while(clientMessage.compareTo("logout")!=0);

            serverMessage = userName + " has quitted.";
            
            reader.close();
            writer.close();
            socket.close();
            server.broadcast(serverMessage,this);
            
        } 
        catch (Exception e) 
        {
            System.out.println("Error in ClientThread : "+e.toString());
        }
    }
    void sendMessage(String message)
    {
        writer.println(message);
    }
    
}