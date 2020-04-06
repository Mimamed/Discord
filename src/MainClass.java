import javax.swing.*;

public class MainClass
{
    static JFrame win = new JFrame("Discord");

    public static void main (String[] args)
    {
        setup();
    }

    private static void setup()
    {
        win.setVisible(true);
        win.setSize(700, 500);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
