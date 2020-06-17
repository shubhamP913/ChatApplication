package ChatApplication;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client 
{
    public static void main(String args[]) throws IOException
    {
        try 
        {
            
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Socket socket = new Socket(serverAddress,6920);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            Thread readMessage = new Thread(new Runnable(){
            
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
            Thread sendMessage = new Thread(new Runnable()
            {
            
                @Override
                public void run() 
                {
                    Console console  = System.console();
                    while(true)
                    {
                        
                        try
                        {
                            String message = console.readLine();
                            writer.println(message);
                            
                        }
                        catch(Exception e)
                        {
                            System.out.println(e.toString());
                        }
                    }
                    
                }
            });

            sendMessage.start();
            readMessage.start();
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
            System.out.println("Error in Client class : "+e.toString());
        }
    }
}