import javax.swing.*;
import java.awt.*;

public abstract class Panel extends JPanel
{
    public void frameAdding()
    {
        MainClass.win.add(this);
    }

    public void buttonDesign(JButton b)
    {
        b.setMargin(new Insets(0,0,0,0));
        b.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        b.setFocusPainted(false);
        b.setForeground(Color.GRAY);
        b.setBackground(Color.BLACK);
    }

    static int ccx(int c)
    {
        int c1 = (int) Math.round(c * MainClass.xSize);
        return c1;
    }

    static int ccy(int c)
    {
        int c1 = (int) Math.round(c * MainClass.ySize);
        return c1;
    }

    abstract void setup ();
}
