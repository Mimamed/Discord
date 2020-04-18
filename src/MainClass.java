import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainClass
{
    static JFrame win = new JFrame("Discord");
    static SendboxPanel SendBox = new SendboxPanel();
    static ContactsPanel Contacts = new ContactsPanel();
    static ChatPanel Chat = new ChatPanel();
    static Panel[] paneler = {SendBox, Chat, Contacts};
    static double xSize = 1, ySize = 1, size = 1;
    static Socket client;
    static OutputStream output;
    static InputStream input;
    static String ipAddress = "95.109.71.23";
    static int port = 7777;


    public static void main (String[] args)
    {
        setup();
    }


    private static void setup()
    {
        try
        {
            clientSetup();
        }
        catch (Exception e)
        {
            System.out.println("något gick fel med clientSetup()");
            MainClass.makeMessage("<html>Somthing went wrong with network <br/>error message:<br/>" + e.getMessage() + "</html>");
        }

        for (int i = 0; i < paneler.length; i++)
        {
            paneler[i].frameAdding();
            paneler[i].setup();
        }
        win.setVisible(true);
        win.setSize(717, 540);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        win.addWindowListener(new steng());
        /*win.setResizable(false);*/

        win.repaint();
    }


    private static void exit()
    {
        try
        {
            client.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        win.dispose();
        System.exit(0);
    }


    private static void clientSetup() throws Exception
    {
        client = new Socket(ipAddress, port);
        input = client.getInputStream();
        output = client.getOutputStream();
    }

    public static void makeMessage(String text)
    {
        JFrame win = new JFrame("Error message");
        JLabel medelande = new JLabel(text);
        win.add(medelande);
        win.setSize(300,200);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }

    static class steng implements WindowListener
    {

        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e)
        {
            exit();
        }

        public void windowClosed(WindowEvent e) {

        }

        public void windowIconified(WindowEvent e) {

        }

        public void windowDeiconified(WindowEvent e) {

        }

        public void windowActivated(WindowEvent e) {

        }

        public void windowDeactivated(WindowEvent e) {

        }
    }

}
