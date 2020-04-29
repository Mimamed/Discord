import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignUpPanel extends Panel
{

    static JTextField username = new JTextField("");
    static JLabel title1 = new JLabel("Chose name");

    void setup()
    {
        MainClass.win.add(this);

        this.setBounds(ccx(0), ccy(0), ccx(700), ccy(500));
        this.setBackground(Color.DARK_GRAY);
        this.add(username);
        this.add(title1);
        this.setLayout(null);
        title1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        title1.setForeground(Color.white);
        username.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        username.setBounds(ccx(this.getBounds().width / 2 - 100), ccy(100), ccx(200), ccy(30));
        title1.setBounds(ccx(this.getBounds().width / 2 - 100), ccy(70), ccx(200), ccy(20));
        username.addKeyListener(new enterListener());
    }

    class enterListener implements KeyListener
    {

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {

        }

        public void keyReleased(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && username.getText().length() < 20 && username.getText().indexOf(';') == -1 && username.getText().indexOf(':') == -1)
            {
                username.setEditable(false);
                MainClass.startConnection(username.getText());
            }
            else if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                MainClass.makeMessage("<html>Username is ether too long or contain \";\" or \":\".<br/>Make sure it's less than 20 characters and doesn't contain \";\".</html>");
            }
        }
    }
}
