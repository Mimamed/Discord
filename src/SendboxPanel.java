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
    static int width = 700, length = 80, port = 7777, delay = 1000, loadingSekuens = 0;
    static JButton sendButton = new JButton("Send");
    static JTextArea message = new JTextArea("");
    static JScrollPane scrol = new JScrollPane(message);
    static Timer timer = new Timer(delay, new LoadingScreen());
    static Socket client;
    static OutputStream output;
    static InputStream input;
    static String ipAddress = "95.109.71.23";
    static byte[] buffer = new byte[1000];

    void setup()
    {
        this.setBounds(0, ContactsPanel.length, width + 1, length + 1);
        this.setLayout(null);
        this.add(sendButton);
        this.add(scrol);

        sendButton.setBounds(ccx(width - 70), ccy(length - 70), ccx(60), ccy(60));
        scrol.setBounds(ccx(ContactsPanel.width + 25), ccy(25), ccx(width - ContactsPanel.width - 120), ccy(length - 50));

        buttonDesign(sendButton);
        message.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        message.setLineWrap(true);
        sendButton.addActionListener(new Send());

        try
        {
            clientSetup();
        }catch (Exception e)
        {
            System.out.println("något gick fel med clientSetup()");
            MainClass.makeMessage("<html>Somthing went wrong with network <br/>error message:<br/>" + e.getMessage() + "</html>");
        }
    }



    private void clientSetup() throws Exception
    {
        client = new Socket(ipAddress, port);
        output = client.getOutputStream();
        input = client.getInputStream();
        buffer = "noooooooob".getBytes();
        output.write(buffer);
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(0,0, width, length);

        g.setColor(Color.BLACK);
        g.drawRect(0,0, width, length);
    }

    public static void sendMessage(String message)
    {

    }

    static class Send implements ActionListener
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
            else
            {
                message.setEditable(false);
                sendMessage(message.getText());
                message.setText("Sending.");
                timer.start();
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
