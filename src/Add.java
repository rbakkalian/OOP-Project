import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.Instant;
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
    private JButton moviesButton;
    private JButton seriesButton;
    private JLabel nameLabel;
    private JLabel genreLabel;
    private JLabel yearLabel;
    private JLabel castLabel;
    private JLabel directorLabel;
    private JLabel titleLabel;
    private int count = 0;

    public Add() {
        String title = "Add Information";
        setContentPane(Add);
        setTitle("Add");
        titleLabel.setText(title);
        Add.setBorder(new EmptyBorder(10, 10, 10, 10));
        image_label.setBorder(new LineBorder(Color.BLUE));
        image_label.setPreferredSize(new Dimension(200, 200));
        setBounds(750, 200, 400, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisibility(false);

        moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleLabel.setText("Add Movie");
                setVisibility(true);
                count = 0;
            }
        });

        seriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(true);
                titleLabel.setText("Add TvShow");
                yearLabel.setText("Years Aired");
                directorLabel.setText("Number of Seasons");
                count = 1;
            }
        });

        comboBox1.addItem(null);
        comboBox1.addItem(MotionPictures.Genre.COMEDY);
        comboBox1.addItem(MotionPictures.Genre.DRAMA);
        comboBox1.addItem(MotionPictures.Genre.THRILLER);
        comboBox1.addItem(MotionPictures.Genre.ROMANCE);
        comboBox1.addItem(MotionPictures.Genre.MYSTERY);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                titleLabel.setText(title);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                MotionPictures.Genre genre = (MotionPictures.Genre) comboBox1.getSelectedItem();

                String image = textField6.getText();
                image = image.replace("\\", "\\\\");

                String s1 = textField4.getText();//cast
                String[] cast = s1.split(",");


                if (count == 0) {
                    String director = textField5.getText();
                    int year = Integer.parseInt(textField3.getText());
                    addMovie(name, cast, year, genre, director, image);
                    JOptionPane.showMessageDialog(Add, "Movie " + name + " added successfully!");
                }else{
                    String years = textField3.getText();
                    int numberOfSeasons = Integer.parseInt(textField5.getText());
                    addTvShow(name, cast, years, genre, numberOfSeasons, image);
                    JOptionPane.showMessageDialog(Add, "TvShow " + name + " added successfully!");
                }

                textField1.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                image_label.setIcon(null);

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
                image_label.setHorizontalAlignment(SwingConstants.CENTER);
            }
        });
    }

    public static void addMovie(String name, String[] cast, int year, MotionPictures.Genre genre, String director, String image){
        Main.movies.add(new Movie(name, cast, year, genre, director, image));
        Main.mCount++;

        String s1 = Arrays.toString(cast);
        long now = Instant.now().toEpochMilli();
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
        builder.append(director+",");
        builder.append(image+",");
        builder.append(now);
        builder.append('\n');
        pw.write(builder.toString());
        pw.close();
        System.out.println("done!");
    }

    public void addTvShow(String name, String[] cast, String year, MotionPictures.Genre genre, int numberOfSeasons, String image){
        Main.tvShows.add(new TvShow(name, cast, year, genre, numberOfSeasons, image));
        Main.tvCount++;

        String s1 = Arrays.toString(cast);
        long now = Instant.now().toEpochMilli();

        PrintWriter pw = null;

        try{
            pw = new PrintWriter(new FileWriter("tvDataBase.csv",true));
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
        builder.append(numberOfSeasons+",");
        builder.append(image+",");
        builder.append(now);
        builder.append('\n');
        pw.write(builder.toString());
        pw.close();
        System.out.println("done!");

    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

    public void setVisibility(boolean b){
        nameLabel.setVisible(b);
        genreLabel.setVisible(b);
        yearLabel.setVisible(b);
        castLabel.setVisible(b);
        directorLabel.setVisible(b);
        textField1.setVisible(b);
        textField3.setVisible(b);
        textField4.setVisible(b);
        addButton.setVisible(b);
        cancelButton.setVisible(b);
        textField5.setVisible(b);
        textField6.setVisible(b);
        attachButton.setVisible(b);
        image_label.setVisible(b);
        comboBox1.setVisible(b);
        moviesButton.setVisible(!b);
        seriesButton.setVisible(!b);
    }
}
