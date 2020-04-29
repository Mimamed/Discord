import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerConection implements Runnable
{

    private static Socket client;
    private static InputStream input;
    private static OutputStream output;
    private static String ipAddress = "95.109.71.23";
    private static int port = 7777, bufferSize = 1000;
    private static byte[] buffer = new byte[bufferSize];
    private static boolean read = true, connected = false;
    private static String username;
    private static Timer exitTimer = new Timer(3000, new Quit());

    public void run()
    {
        try
        {
            clientSetup();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private static void clientSetup() throws Exception
    {
        client = new Socket(ipAddress, port);
        output = client.getOutputStream();
        input = client.getInputStream();
        connected = true;
        sendMessage(username, "server", '#');
        MainClass.userName = username;
        sendMessage("", "server", '?');

        if(input.read(buffer) == -1)
        {
            if (read)
            {
                MainClass.makeMessage("<html>Could not connect to server<br/>Error message: .input.read() returned -1<br/>Will exit in 3 seconds...</html>");
                exitTimer.start();
            }
        }
        else
        {
            readMessage(new String(buffer));
            MainClass.hideConnectingScreen(true);

            while (read)
            {
                if(input.read(buffer) == -1)
                {
                    System.out.println("stäng");
                    if (read)
                    {
                        MainClass.makeMessage("<html>Could not connect to server<br/>Error message: .input.read() returned -1<br/>Will exit in 3 seconds...</html>");
                        exitTimer.start();
                        read = false;
                    }
                    else
                    {
                        client.close();
                    }
                }
                else
                {
                    readMessage(new String(buffer));
                }
            }
        }
    }

    public static void setUsername(String username)
    {
        ServerConection.username = username;
    }


    public static void readMessage(String message)
    {
        if (message.substring(0, 8).equals("{6969420"))
        {
            switch (message.charAt(8))
            {
                case '?':
                    String tempMessage = message.substring(9, message.indexOf(";"));
                    boolean last = false;
                    int tempPosition = 0;

                    if (tempMessage.indexOf(":", tempPosition) == -1)
                    {
                        last = true;
                    }

                    while (!last)
                    {
                        MainClass.users.add(tempMessage.substring(tempPosition, tempMessage.indexOf(":", tempPosition)));
                        tempPosition = tempMessage.indexOf(":", tempPosition) + 1;
                        if (tempMessage.indexOf(":", tempPosition) == -1)
                        {
                            last = true;
                        }
                    }
                    System.out.println(tempMessage + " -:::- " + MainClass.users.get(0));
                    break;

                case '+'://få medelande
                    break;

                case '#':
                    break;

                default:
                    System.out.println("teeeeeeeeeest, fel med medelande");
            }
        }
    }

    public static void sendMessage(String message, String target, char messageType) // '+' = skicka medelanden, '!' = broadcast medelande, '?' = begär uppdatering av användar lista, '#' = bestämmer användarnamnet(bör endast användas en gång)
    {

        if (target.equals("server"))
        {
            try
            {
                buffer = ("{6969420" + messageType + message + ";").getBytes();
                output.write(buffer);
                buffer = new byte[bufferSize];
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {

            try
            {
                buffer = ("{6969420" + messageType + message + ":" + target + ";").getBytes();
                output.write(buffer);
                buffer = new byte[bufferSize];
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void exit()
    {
        try
        {
            if (connected)
            {
                sendMessage("", "server", '¤');
                read = false;
                connected = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    static class Quit implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            MainClass.exit();
        }
    }
}
