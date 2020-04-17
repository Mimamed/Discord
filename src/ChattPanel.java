import javax.swing.*;
import java.awt.*;

public class ChattPanel extends Panel
{
    static int width = 500, length = ContactsPanel.length;
    static JTextArea messages = new JTextArea("");
    static JScrollPane scrol = new JScrollPane(messages);

    void setup()
    {
        this.setBounds(ccx(ContactsPanel.width), ccy(0), ccx(width + 1), ccy(length + 1));
        this.add(scrol);
        scrol.setBounds(ccx(1), ccy(1), ccx(width - 2), ccy(length - 2));
        messages.setBackground(Color.darkGray);
        messages.setForeground(Color.white);
        scrol.setBorder(null);
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,width, length);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, length);
    }
}