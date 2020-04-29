import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendboxPanel extends Panel
{
    static int width = 700, length = 80, delay = 1000, loadingSekuens = 0;
    static JButton sendButton = new JButton("Send");
    static JTextArea message = new JTextArea("");
    static JScrollPane scrol = new JScrollPane(message);
    static Timer timer = new Timer(delay, new LoadingScreen());
    static byte[] buffer = new byte[1000];

    void setup()
    {
        this.setBounds(0, ContactsPanel.length, width + 1, length + 1);
        this.add(sendButton);
        this.add(scrol);

        sendButton.setBounds(ccx(width - 70), ccy(length - 70), ccx(60), ccy(60));
        scrol.setBounds(ccx(ContactsPanel.width + 25), ccy(25), ccx(width - ContactsPanel.width - 120), ccy(length - 50));

        buttonDesign(sendButton);
        message.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        message.setLineWrap(true);
        sendButton.addActionListener(new Send());

    }




    public void paintComponent(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(0,0, width, length);

        g.setColor(Color.BLACK);
        g.drawRect(0,0, width, length);
    }

    public void sendMessage(String message)
    {
        try
        {
            MainClass.Listener.sendMessage(message, "someone", '+');//Fixa senare
            timer.stop();
            this.message.setText("");
            this.message.setEditable(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    class Send implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if(message.getText().length() > 200)
            {
                try
                {
                    message.setText(message.getText(0,200));
                    MainClass.makeMessage("<html>Too long message.<br/>We cut away the nessecary part, now try again</html>");
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else if (message.getText().indexOf(';') != -1 || message.getText().indexOf(':') != -1)
            {
                try
                {
                    MainClass.makeMessage("<html>Message can not contain \";\" or \":\".<br/>Please use a different name</html>");
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                message.setEditable(false);
                String text = message.getText();
                message.setText("Sending.");
                timer.start();
                sendMessage(text);
            }
            System.out.println(message.getLineCount() + " - " + message.getColumns() + "\n" + message.getText());
        }
    }



    static class LoadingScreen implements  ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (loadingSekuens > 2)
            {
                loadingSekuens = 0;
            }
            else
            {
                loadingSekuens++;
            }
            message.setText("Sending.");

            for (int i = 0; i < loadingSekuens; i++)
            {
                message.append(".");
            }
        }
    }
}
