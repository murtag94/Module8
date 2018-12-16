import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Pattern;

public class SecondForm extends JPanel {
    private JPanel rootPanel;
    private JTextField fioTextField;
    private JButton OKButton;
    private JProgressBar progressBar1;

    {
        fioTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                progress();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                progress();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
    }

    public JTextField getFioTextField() {
        return fioTextField;
    }

    public JButton getOKButton() {
        return OKButton;
    }

    protected void progress()
    {
        String text = fioTextField.getText().trim();
        if(text.equals(""))
        {
            progressBar1.setValue(0);
        }
        else{
            progressBar1.setValue(text.split("\\s+").length);
        }
    }
}
