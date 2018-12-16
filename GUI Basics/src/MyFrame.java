import javax.swing.*;

public class MyFrame extends JFrame
{
    private Form form = new Form();

    public MyFrame()
    {
        setContentPane(form);
        setSize(800, 600);
    }

    public static void main(String[] args)
    {
        MyFrame frame = new MyFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
