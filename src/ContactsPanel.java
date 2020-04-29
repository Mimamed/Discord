import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ContactsPanel extends Panel
{
    static int length = 420, width = 200;
    static List<JButton> contacts = new ArrayList<JButton>();
    static JPanel scrollPanel = new JPanel();
    static JScrollPane scrollContacts = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    static List<JButton> chatButton = new ArrayList<JButton>();

    void setup()
    {
        this.setBounds(ccx(0),ccy(0),ccx(width + 1),ccy(length + 1));
        this.add(scrollContacts);
        scrollContacts.setBounds(ccx(1), ccy(1), ccx(width - 2), ccy(length - 2));
        scrollPanel.setPreferredSize(new Dimension(scrollContacts.getViewport().getWidth(), 0));
        scrollPanel.setLayout(null);
    }

    public static void uppdateContacts(List<Integer> clientKeys)
    {
        chatButton = new ArrayList<JButton>();

        for(int i = 0; i < clientKeys.size(); i++)
        {
            chatButton.add(new JButton(clientKeys.get(i).toString()));
            chatButton.get(i).addActionListener(new ContactSwitch());
        }
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(ccx(0), ccy(0), ccx(width), ccy(length));

        g.setColor(Color.BLACK);
        g.drawRect(0,0, width, length);
    }


    static class ContactSwitch implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            JButton temp = (JButton) e.getSource();
            System.out.println(temp.getText());
        }
    }
}
