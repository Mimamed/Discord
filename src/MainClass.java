import javax.swing.*;

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
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*win.setResizable(false);*/

        win.repaint();
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

}
