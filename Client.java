
import java.io.*;
import java.net.*;
import java.util.*;

class Client 
{
    public static void main(String args[]) throws IOException
    {
        try 
        {
            
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Socket socket = new Socket(serverAddress,6920);
            System.out.println("Connected to server");
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            Thread readMessage = new Thread(new Runnable(){ //responsible for reading input from the server and printing it to the console
            
                @Override
                public void run() {
                    while(true)
                    {
                        try 
                        {

                            String message = reader.readLine();
                            System.out.println(message);
                        }
                        catch (Exception e) 
                        {
                            e.printStackTrace();
                        }
                    }
                }
            });
            readMessage.start();
            Thread sendMessage = new Thread(new Runnable() //responsible for reading input from the user and sending it to the server
            {
            
                @Override
                public void run() 
                {
                    Console console = System.console();
 
                    String userName = console.readLine("\nEnter your name: ");
                    writer.println(userName);
                    String text;
            
                    do 
                    {
                        text = console.readLine("[" + userName + "]: ");
                        writer.println(text);
            
                    } while (!text.equals("logout"));
            
                    try 
                    {
                        socket.close();
                    } 
                    catch (IOException ex) 
                    {
                        System.out.println("Error writing to server: " + ex.getMessage());
                    }
                    
                }
            });

            sendMessage.start();
            
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
            System.out.println("Error in Client class : "+e.toString());
        }
    }
}
