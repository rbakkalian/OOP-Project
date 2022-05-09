import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.Instant;
import java.util.Arrays;

public class Edit extends JFrame {
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton editButton;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel image_label;
    private JButton attachButton;
    private JComboBox<MotionPictures.Genre> comboBox1;
    private JPanel Edit;
    private JLabel nameLabel;
    private JLabel genreLabel;
    private JLabel yearLabel;
    private JLabel castLabel;
    private JLabel directorLabel;
    private JButton moviesButton;
    private JButton seriesButton;
    private JLabel titleLabel;
    private JButton searchButton;
    private int count = 0;

    public Edit() {
        setContentPane(Edit);
        String title = "Edit Information";
        setTitle("Edit");
        image_label.setBorder(new LineBorder(Color.BLUE));
        image_label.setPreferredSize(new Dimension(200, 200));
        Edit.setBorder(new EmptyBorder(10, 10, 10, 10));
        setBounds(750, 200, 400, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisibility(false);

        moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleLabel.setText("Edit Movie");
                setVisibility(true);
                count = 0;
                JOptionPane.showMessageDialog(Edit, "Enter appropriate info in any text-field");
            }
        });

        seriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(true);
                titleLabel.setText("Edit TvShow");
                yearLabel.setText("Years Broadcasted");
                directorLabel.setText("Number of Seasons");
                count = 1;
                JOptionPane.showMessageDialog(Edit, "Enter appropriate info in any text-field");
            }
        });

        comboBox1.addItem(null);
        comboBox1.addItem(MotionPictures.Genre.COMEDY);
        comboBox1.addItem(MotionPictures.Genre.DRAMA);
        comboBox1.addItem(MotionPictures.Genre.THRILLER);
        comboBox1.addItem(MotionPictures.Genre.ROMANCE);
        comboBox1.addItem(MotionPictures.Genre.MYSTERY);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0)
                    searchMovie();
                else searchTvShow();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleLabel.setText(title);
                setVisibility(false);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    deleteMovie();
                    String name = textField1.getText();
                    MotionPictures.Genre genre = (MotionPictures.Genre) comboBox1.getSelectedItem();

                    String image = textField6.getText();
                    image = image.replace("\\", "\\\\");

                    String s1 = textField4.getText();//cast
                    String[] cast = s1.split(",");

                    String director = textField5.getText();
                    int year = Integer.parseInt(textField3.getText());
                    addMovie(name, cast, year, genre, director, image);
                    JOptionPane.showMessageDialog(Edit, "Movie " + name + " edited successfully!");

                    textField1.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    image_label.setIcon(null);
                } else {
                    deleteTvShow();
                    String name = textField1.getText();
                    MotionPictures.Genre genre = (MotionPictures.Genre) comboBox1.getSelectedItem();

                    String image = textField6.getText();
                    image = image.replace("\\", "\\\\");

                    String s1 = textField4.getText();//cast
                    String[] cast = s1.split(",");

                    String years = textField3.getText();
                    int numberOfSeasons = Integer.parseInt(textField5.getText());
                    addTvShow(name, cast, years, genre, numberOfSeasons, image);
                    JOptionPane.showMessageDialog(Edit, "TvShow " + name + " edited successfully!");


                    textField1.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    image_label.setIcon(null);
                }
            }
        });
    }

    public void searchMovie() {
        boolean isDeleted = false;
        String x = textField1.getText();
        String line = "";
        String lineFromRemovedDb = "";
        long deletionDate = Long.MIN_VALUE;
        String delimiter = ",";

        BufferedReader br;
        BufferedReader deletedBr;
        try {
            br = new BufferedReader(new FileReader("dataBase.csv"));
            deletedBr = new BufferedReader(new FileReader("dataBaseDeleted.csv"));

            while ((lineFromRemovedDb = deletedBr.readLine()) != null) {
                String[] filmDataDeleted = lineFromRemovedDb.split(delimiter);
                if (x.equals(filmDataDeleted[0])) {
                    deletionDate = Long.parseLong(filmDataDeleted[7]);
                    isDeleted = true;
                }
            }

            while ((line = br.readLine()) != null) {
                String[] filmData = line.split(delimiter);    // use comma as separator
                if (x.equals(filmData[0]) && (!isDeleted ||(isDeleted && Long.parseLong(filmData[6]) > deletionDate) ) ) {

                        textField1.setText(filmData[0]);
//                            comboBox1 = new JComboBox<MotionPictures.Genre>((String) filmData[1]);
                        textField3.setText(filmData[2]);
                        textField4.setText(filmData[3]);
                        textField5.setText(filmData[4]);
                        textField6.setText(filmData[5]);}



            }
            br.close();
            deletedBr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void searchTvShow() {
        boolean isDeleted = false;
        String x = textField1.getText();
        String line = "";
        String lineFromRemovedDb = "";
        long deletionDate = Long.MIN_VALUE;
        String delimiter = ",";

        BufferedReader br;
        BufferedReader deletedBr;
        try {
            br = new BufferedReader(new FileReader("tvDataBase.csv"));
            deletedBr = new BufferedReader(new FileReader("dataBaseDeleted.csv"));

            while ((lineFromRemovedDb = deletedBr.readLine()) != null) {
                String[] filmDataDeleted = lineFromRemovedDb.split(delimiter);
                if (x.equals(filmDataDeleted[0])) {
                    deletionDate = Long.parseLong(filmDataDeleted[7]);
                    isDeleted = true;
                }
            }

            while ((line = br.readLine()) != null) {
                String[] filmData = line.split(delimiter);    // use comma as separator
                if (x.equals(filmData[0]) && (!isDeleted ||(isDeleted && Long.parseLong(filmData[6]) > deletionDate) ) ) {


                    String info = "Name: " + filmData[0] + "\n" +
                            "Genre: " + filmData[1] + "\n" +
                            "Year Broadcasted: " + filmData[2] + "\n" +
                            "Cast: " + filmData[3] + "\n" +
                            "Number of Seasons: " + filmData[4] + "\n";

                    textField1.setText(filmData[0]);
//                            comboBox1 = new JComboBox<MotionPictures.Genre>((String) filmData[1]);
                    textField3.setText(filmData[2]);
                    textField4.setText(filmData[3]);
                    textField5.setText(filmData[4]);
//                    textField6.setText(filmData[]);
                }

            }
            br.close();
            deletedBr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteMovie() {
        String response = "";
        String x = textField1.getText();
        String line = "";
        String delimiter = ",";
        BufferedReader bufferedReader;
        PrintWriter printWriter;
        long now = Instant.now().toEpochMilli();

        try {
            bufferedReader = new BufferedReader(new FileReader("dataBase.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(x)) {
                    String[] filmData = line.split(delimiter);
                    if (x.length() == 0) {
                        response = "please enter text";
                        break;
                    } else if (x.equals(filmData[0])) {
                        printWriter = new PrintWriter(new BufferedWriter(new FileWriter("dataBaseDeleted.csv", true)));
                        StringBuilder builder = new StringBuilder();
                        builder.append(filmData[0] + ",");
                        builder.append(filmData[1] + ",");
                        builder.append(filmData[2] + ",");
                        builder.append(filmData[3] + ",");
                        builder.append(filmData[4] + ",");
                        builder.append(filmData[5] + ",");
                        builder.append("deleted" + ",");
                        builder.append(now);
                        builder.append('\n');
                        printWriter.write(builder.toString());
                        printWriter.close();
                        response = "movie deleted";
                        break;
                    }
                } else {
                    response = "not found";
                }
            }
            JOptionPane.showMessageDialog(Edit, response);
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void addMovie(String name, String[] cast, int year, MotionPictures.Genre genre, String director, String image) {
        Main.movies.add(new Movie(name, cast, year, genre, director, image));
        Main.mCount++;

        String s1 = Arrays.toString(cast);
        long now = Instant.now().toEpochMilli();

        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileWriter("dataBase.csv", true));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(name + ",");
        builder.append(genre + ",");
        builder.append(year + ",");
        builder.append(s1 + ",");
        builder.append(director + ",");
        builder.append(image + ",");
        builder.append(now);
        builder.append('\n');
        pw.write(builder.toString());
        pw.close();
        System.out.println("done!");
    }


    public void deleteTvShow() {
        String response = "";
        String x = textField1.getText();
        String line = "";
        String delimiter = ",";
        BufferedReader bufferedReader;
        PrintWriter printWriter;
        long now = Instant.now().toEpochMilli();

        try {
            bufferedReader = new BufferedReader(new FileReader("tvDataBase.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(x)) {
                    String[] filmData = line.split(delimiter);
                    if (x.length() == 0) {
                        response = "please enter text";
                        break;
                    } else if (x.equals(filmData[0])) {
                        printWriter = new PrintWriter(new BufferedWriter(new FileWriter("dataBaseDeleted.csv", true)));
                        StringBuilder builder = new StringBuilder();
                        builder.append(filmData[0] + ",");
                        builder.append(filmData[1] + ",");
                        builder.append(filmData[2] + ",");
                        builder.append(filmData[3] + ",");
                        builder.append(filmData[4] + ",");
                        builder.append(filmData[5] + ",");
                        builder.append("deleted" + ",");
                        builder.append(now);
                        builder.append('\n');
                        printWriter.write(builder.toString());
                        printWriter.close();
                        response = "TvShow deleted";
                        break;
                    }
                } else {
                    response = "not found";
                }
            }
            JOptionPane.showMessageDialog(Edit, response);
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addTvShow(String name, String[] cast, String year, MotionPictures.Genre genre, int numberOfSeasons, String image) {
        Main.tvShows.add(new TvShow(name, cast, year, genre, numberOfSeasons, image));
        Main.tvCount++;

        String s1 = Arrays.toString(cast);
        long now = Instant.now().toEpochMilli();

        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileWriter("tvDataBase.csv", true));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(name + ",");
        builder.append(genre + ",");
        builder.append(year + ",");
        builder.append(s1 + ",");
        builder.append(numberOfSeasons + ",");
        builder.append(image + ",");
        builder.append(now);
        builder.append('\n');
        pw.write(builder.toString());
        pw.close();
        System.out.println("done!");

    }


    public void setVisibility(boolean b) {
        nameLabel.setVisible(b);
        genreLabel.setVisible(b);
        yearLabel.setVisible(b);
        castLabel.setVisible(b);
        directorLabel.setVisible(b);
        textField1.setVisible(b);
        textField3.setVisible(b);
        textField4.setVisible(b);
        editButton.setVisible(b);
        cancelButton.setVisible(b);
        textField5.setVisible(b);
        textField6.setVisible(b);
        attachButton.setVisible(b);
        image_label.setVisible(b);
        comboBox1.setVisible(b);
        searchButton.setVisible(b);
        moviesButton.setVisible(!b);
        seriesButton.setVisible(!b);
    }
}
