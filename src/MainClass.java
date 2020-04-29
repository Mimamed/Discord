import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class MainClass
{
    static JFrame win = new JFrame("Discord");
    static ConnectingPanel Connecting = new ConnectingPanel();
    static SignUpPanel SignUp = new SignUpPanel();
    static SendboxPanel SendBox = new SendboxPanel();
    static ContactsPanel Contacts = new ContactsPanel();
    static ChatPanel Chat = new ChatPanel();
    static ServerConection Listener = new ServerConection();
    static Thread connection = new Thread(Listener);
    static Panel[] paneler = {SendBox, Chat, Contacts, Connecting};
    static double xSize = 1, ySize = 1, size = 1;
    static int usersAmount, clientKey;
    static String userName = "";
    static List<String> users = new ArrayList<String>();


    public static void main (String[] args)
    {
        setup();
    }

    public static void startConnection(String userName)
    {

        Listener.setUsername(userName);
        try
        {
            connection.start();
        }
        catch (Exception e)
        {
            System.out.println("något gick fel med clientSetup()");
            MainClass.makeMessage("<html>Somthing went wrong with network <br/>error message:<br/>" + e.getMessage() + "</html>");
        }
        win.remove(SignUp);
        win.revalidate();
        win.repaint();
        signIn();
    }

    public static void signIn()
    {


        for (int i = 0; i < paneler.length; i++)
        {
            paneler[i].frameAdding();
            paneler[i].setup();
        }
    }

    private static void setup()
    {
        win.setVisible(true);
        win.setSize(717, 540);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        win.addWindowListener(new Close());
        win.setResizable(false);
        SignUp.setup();

        win.repaint();
    }

    public static void hideConnectingScreen(boolean hide)
    {
        for (int i = 0; i < paneler.length - 1; i++)
        {
            paneler[i].setVisible(hide);
        }
    }


    public static void exit()
    {
        try
        {
            Listener.exit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        win.dispose();
        System.exit(0);
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

    static class Close implements WindowListener
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
