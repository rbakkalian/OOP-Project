import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;

public class Add extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel Add;
    private JTextField textField5;
    private JTextField textField6;
    private JButton attachButton;
    private JLabel image_label;

    public Add() {
        setContentPane(Add);
        setTitle("Add Movie");
        image_label.setBorder(new LineBorder(Color.BLUE));
        image_label.setPreferredSize(new Dimension(200, 200));
        setBounds(750, 200, 400, 600);
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
                String image = textField6.getText();
                image = image.replace("\\", "\\\\");

                String s1 = textField4.getText();
                String[] cast = s1.split(",");



                PrintWriter pw = null;

                try{
                    pw = new PrintWriter(new FileWriter("dataBase.csv",true));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                StringBuilder builder  = new StringBuilder();
                builder.append(name+",");
                builder.append(genre+",");
                builder.append(year+",");
                builder.append(s1+",");
                builder.append(ageCategory);
                builder.append('\n');
                pw.write(builder.toString());
                pw.close();
                JOptionPane.showMessageDialog(Add, "Movie " + name + " added successfully!");


                /*FileWriter writer = null;
                try {
                    writer = new FileWriter("dataBase.csv",true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                for (int j = 0; j < 5; j++) {
                    try {
                        writer.append(String.valueOf(data[j]));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        writer.append("\n");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }*/

                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");

            }
        });
        attachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();
                String fileName = file.getAbsolutePath();
                textField6.setText(fileName);
                Image getAbsolutePath = null;
                ImageIcon icon = new ImageIcon(fileName);
                Image image = icon.getImage().getScaledInstance(image_label.getWidth(), image_label.getHeight(), Image.SCALE_SMOOTH);
                image_label.setIcon(icon);
            }
        });
    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
}
