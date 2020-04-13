import javax.swing.*;
import java.awt.*;

public class ContactsPanel extends Panel
{
    static int length = 420, width = 200;

    void setup()
    {
        this.setBounds(ccx(0),ccy(0),ccx(width + 1),ccy(length + 1));
        this.repaint();
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(ccx(0), ccy(0), ccx(width), ccy(length));

        g.setColor(Color.BLACK);
        g.drawRect(0,0, width, length);
    }
}
