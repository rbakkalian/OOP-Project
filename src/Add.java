import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Add extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel Add;
    private JTextField textField5;

    public Add() {
        setContentPane(Add);
        setTitle("Add Movie");
        setBounds(600, 200, 400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                String genre = textField2.getText();
                int year = Integer.parseInt(textField3.getText());
                String ageCategory = textField5.getText();

                String s1 = textField4.getText();
                String[] cast = s1.split(",");

                Form1.movies.add(new Movie(name, cast, year, genre, ageCategory));
                JOptionPane.showMessageDialog(Add, "Movie " + name + " added successfully!" + Form1.mCount);
                Form1.mCount++;

                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");

            }
        });
    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
}
