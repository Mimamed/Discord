import javax.swing.*;
import java.awt.*;

public class ChattPanel extends Panel
{
    static int width = 500, length = ContactsPanel.length;

    void setup()
    {
        this.setBounds(ccx(ContactsPanel.width), ccy(0), ccx(width + 1), ccy(length + 1));
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,width, length);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, length);
    }
}
