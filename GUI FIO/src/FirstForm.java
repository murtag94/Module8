import javax.swing.*;

public class FirstForm extends JPanel {
    private JPanel rootPanel;
    private JTextField surnameTextField;
    private JTextField nameTextField;
    private JTextField patronymicTextField;
    private JButton OKButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
    }

    public JTextField getSurnameTextField() {
        return surnameTextField;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getPatronymicTextField() {
        return patronymicTextField;
    }

    public JButton getOKButton() {
        return OKButton;
    }
}
