import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Arrays;

public class Add extends JFrame{
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel Add;
    private JTextField textField5;
    private JTextField textField6;
    private JButton attachButton;
    private JLabel image_label;
    private JComboBox<MotionPictures.Genre> comboBox1;
    private JRadioButton tvShowRadioButton;
    private JRadioButton movieRadioButton;

    public Add() {
        setContentPane(Add);
        setTitle("Add Movie");
        Add.setBorder(new EmptyBorder(10, 10, 10, 10));
        image_label.setBorder(new LineBorder(Color.BLUE));
        image_label.setPreferredSize(new Dimension(200, 200));
        setBounds(750, 200, 400, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        comboBox1.addItem(null);
        comboBox1.addItem(MotionPictures.Genre.COMEDY);
        comboBox1.addItem(MotionPictures.Genre.DRAMA);
        comboBox1.addItem(MotionPictures.Genre.THRILLER);
        comboBox1.addItem(MotionPictures.Genre.ROMANCE);
        comboBox1.addItem(MotionPictures.Genre.MYSTERY);


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
                MotionPictures.Genre genre = (MotionPictures.Genre) comboBox1.getSelectedItem();
                int year = Integer.parseInt(textField3.getText());
                String director = textField5.getText();
                String image = textField6.getText();
                image = image.replace("\\", "\\\\");

                String s1 = textField4.getText();//cast
                String[] cast = s1.split(",");

                add(name, cast, year, genre, director, image);
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

    public static void add(String name, String[] cast, int year, MotionPictures.Genre genre, String director, String image){
        Form1.movies.add(new Movie(name, cast, year, genre, director, image));
        Form1.mCount++;

        String s1 = Arrays.toString(cast);

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
        builder.append(director);
        builder.append('\n');
        pw.write(builder.toString());
        pw.close();
        System.out.println("done!");

    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
}
