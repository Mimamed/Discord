import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsPanel extends Panel
{
    static int length = 420, width = 200;
    static List<JButton> contacts = new ArrayList<JButton>();
    static JPanel scrollPanel = new JPanel();
    static JScrollPane scrollContacts = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    static JButton nob = new JButton();

    void setup()
    {
        this.setBounds(ccx(0),ccy(0),ccx(width + 1),ccy(length + 1));
        this.add(scrollContacts);
        scrollContacts.setBounds(ccx(1), ccy(1), ccx(width - 2), ccy(length - 2));
        scrollPanel.setPreferredSize(new Dimension(scrollContacts.getViewport().getWidth(), 1000));
        scrollPanel.setLayout(null);
        scrollPanel.add(nob);
        nob.setBounds(ccx(1), ccy(length - 50), ccx(width +300), ccy(length +100));
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(ccx(0), ccy(0), ccx(width), ccy(length));

        g.setColor(Color.BLACK);
        g.drawRect(0,0, width, length);
    }
}
