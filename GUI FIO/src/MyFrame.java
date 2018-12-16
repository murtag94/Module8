import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

public class MyFrame extends JFrame
{
    private FirstForm firstForm = new FirstForm();
    private SecondForm secondForm = new SecondForm();
    private String surname = " ";
    private String name = " ";
    private String patronymic = " ";

    public MyFrame()
    {
        setContentPane(firstForm);
        pack();
        setMinimumSize(getSize());
        setSize(400, 200);

        firstForm.getOKButton().addActionListener(e -> textFieldValidation());
        secondForm.getOKButton().addActionListener(e -> textFieldValidation());

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(
                KeyEvent.VK_ENTER,
                InputEvent.CTRL_DOWN_MASK,
                false),
                "switchForm");
        getRootPane().getActionMap().put("switchForm", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldValidation();
            }
        });
    }

    public static void main(String[] args)
    {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        MyFrame frame = new MyFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    protected void textFieldValidation()
    {
        if (getContentPane() == firstForm)
        {
            surname = firstForm.getSurnameTextField().getText().trim();
            name = firstForm.getNameTextField().getText().trim();
            patronymic = firstForm.getPatronymicTextField().getText().trim();

            if(surname.equals(""))
            {
                JOptionPane.showMessageDialog(
                        firstForm,
                        "Необходимо ввести фамилию!",
                        "Окно сообщения",
                        JOptionPane.WARNING_MESSAGE);

                firstForm.getSurnameTextField().setText("");
                firstForm.getSurnameTextField().requestFocus();
            }
            else if(!isName(surname))
            {
                JOptionPane.showMessageDialog(
                        firstForm,
                        "Фамилия введена некорректно!",
                        "Окно сообщения",
                        JOptionPane.ERROR_MESSAGE);

                firstForm.getSurnameTextField().requestFocus();
            }
            else if(name.equals(""))
            {
                JOptionPane.showMessageDialog(
                        firstForm,
                        "Необходимо ввести имя!",
                        "Окно сообщения",
                        JOptionPane.WARNING_MESSAGE);

                firstForm.getNameTextField().setText("");
                firstForm.getNameTextField().requestFocus();
            }
            else if(!isName(name))
            {
                JOptionPane.showMessageDialog(
                        firstForm,
                        "Имя введено некорректно!",
                        "Окно сообщения",
                        JOptionPane.ERROR_MESSAGE);

                firstForm.getNameTextField().requestFocus();
            }
            else if(patronymic.equals(""))
            {
                int option = JOptionPane.showConfirmDialog(
                        firstForm,
                        "Желаете ввести отчество?",
                        "Окно подтверждения",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if(option == JOptionPane.YES_OPTION)
                {
                    firstForm.getPatronymicTextField().setText("");
                    firstForm.getPatronymicTextField().requestFocus();
                }
                else{
                    switchFirstForm();
                }
            }
            else if(!isName(patronymic))
            {
                JOptionPane.showMessageDialog(
                        firstForm,
                        "Отчество введено некорректно!",
                        "Окно сообщения",
                        JOptionPane.ERROR_MESSAGE);

                firstForm.getPatronymicTextField().requestFocus();
            }
            else{
                switchFirstForm();
            }
        }
        else
        {
            if(!isFullName(secondForm.getFioTextField().getText().trim()))
            {
                JOptionPane.showMessageDialog(
                        secondForm,
                        "Ф.И.О введено некорректно!",
                        "Окно сообщения",
                        JOptionPane.ERROR_MESSAGE);

                secondForm.getFioTextField().requestFocus();
            }
            else {
                switchSecondForm();
            }
        }

    }

    protected void switchFirstForm()
    {
        setContentPane(secondForm);
        getContentPane().revalidate();
        secondForm.getFioTextField().setText(surname + " " + name + " " + patronymic);
    }

    protected void switchSecondForm()
    {
        String[] fio = secondForm.getFioTextField().getText().trim().split("\\s+");
        surname = fio[0];
        name = fio[1];
        if(fio.length >= 3)
        {
            patronymic = fio[2];
        } else{
            patronymic = " ";
        }

        setContentPane(firstForm);
        getContentPane().revalidate();
        firstForm.getSurnameTextField().setText(surname);
        firstForm.getNameTextField().setText(name);
        firstForm.getPatronymicTextField().setText(patronymic);
    }

    protected static boolean isName(String string) {
        return Pattern.compile("[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+").matcher(string).matches();
    }

    protected static boolean isFullName(String string) {
        return Pattern.compile("[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+\\s[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+(\\s[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+)?").matcher(string).matches();
    }
}
