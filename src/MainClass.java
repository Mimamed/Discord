import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainClass
{
    static JFrame win = new JFrame("Discord");
    static ContactsPanel Contacts = new ContactsPanel();
    static ChattPanel Chatt = new ChattPanel();
    static SendboxPanel SendBox = new SendboxPanel();
    static Panel[] paneler = {Chatt, SendBox, Contacts};
    static double xSize = 1, ySize = 1, size = 1;


    public static void main (String[] args)
    {
        setup();
    }

    private static void setup()
    {

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
            SendboxPanel.client.close();
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
