import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edit extends JFrame{
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton editButton;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel image_label;
    private JButton attachButton;
    private JComboBox comboBox1;
    private JPanel Edit;

    public Edit() {
        setContentPane(Edit);
        setTitle("Edit Movie");
        Edit.setBorder(new EmptyBorder(10, 10, 10, 10));
        setBounds(600, 200, 400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
