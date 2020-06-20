
import java.util.*;
import java.io.*;
import java.net.*;

class Server
{
    private Set<String> userNames = new HashSet<>();
    private Set<ClientThread> users = new HashSet<>();
    static private int port = 6920;
    static private Scanner in ;
    public static void main(final String args[]) 
    {
        Server ss = new Server();
        ss.execute();
    }
    public void execute()
    {
        try 
        {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Waiting for client...");
            while (true) 
            {
                Socket socket = server.accept();
                System.out.println("New user connected");
                ClientThread newUser = new ClientThread(socket,this);
                users.add(newUser);    
                newUser.start();
            }
            // server.close();
        } catch (final Exception e) 
        {    
            System.out.println("Error in Server : "+e.toString());
        }
    }
    void broadcast(String message,ClientThread exclude) 
    {
        for (ClientThread aUser : users) 
        {
            if(aUser!=exclude)
                aUser.sendMessage(message);
        }
    }
}