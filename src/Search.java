import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Search extends JFrame {
    private JTextField textField1;
    private JButton searchButton;
    private JButton cancelButton;
    private JPanel Search;
    private JLabel nameLabel;
    private JButton moviesButton;
    private JButton seriesButton;
    private JLabel titleLabel;
    private JTextPane textPane1;
    private int count = 0;

    public Search() {
        String title = "Search Content";
        setContentPane(Search);
        setTitle("Search");
        titleLabel.setText(title);
        Search.setBorder(new EmptyBorder(10, 10, 10, 10));
        setBounds(600, 200, 500, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textPane1.setEditable(false);

        setVisibility(false);

        moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleLabel.setText("Search for Movie");
                setVisibility(true);
                count = 0;
            }
        });

        seriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(true);
                titleLabel.setText("Search for TvShow");
                count = 1;
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                titleLabel.setText(title);
                textField1.setText("");
                textPane1.setText("");
            }
        });

        Icon icon2 = new ImageIcon("src/assets/search-icon-png-9978.png");
        searchButton.setIcon(icon2);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    searchMovie();
                } else searchTvShow();
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
                String[] filmData = line.split(delimiter);
                if (x.equals(filmData[0]) && (!isDeleted ||(isDeleted && Long.parseLong(filmData[6]) > deletionDate) ) ) {

                    if (x.length() == 0) {
                        String noTextInfo = "Please enter a keyword for a lookup";
                        textPane1.setText(noTextInfo);
                    } else {
                        String info = "Name: " + filmData[0] + "\n" +
                                "Genre: " + filmData[1] + "\n" +
                                "Release Date: " + filmData[2] + "\n" +
                                "Cast: " + filmData[3] + "\n" +
                                "Director: " + filmData[4] + "\n";
                        ImageIcon imageIcon = new ImageIcon(filmData[5]);

                        textPane1.setText(info);
                        textPane1.insertIcon(imageIcon);
                        break;
                    }


                }else {
                    String noMoviesInfo = "No movies were found";
                    textPane1.setText(noMoviesInfo);
                }

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
                String[] filmData = line.split(delimiter);
                if (x.equals(filmData[0]) && (!isDeleted ||(isDeleted && Long.parseLong(filmData[6]) > deletionDate) ) ) {
                    if (x.length() == 0) {
                        String noTextInfo = "Please enter a keyword for a lookup";
                        textPane1.setText(noTextInfo);
                    } else {    // use comma as separator
                        String info = "Name: " + filmData[0] + "\n" +
                                "Genre: " + filmData[1] + "\n" +
                                "Year Broadcasted: " + filmData[2] + "\n" +
                                "Cast: " + filmData[3] + "\n" +
                                "Number of Seasons: " + filmData[4] + "\n";
                        ImageIcon imageIcon = new ImageIcon(filmData[5]);

                        textPane1.setText(info);
                        textPane1.insertIcon(imageIcon);
                        break;
                    }

                }else {
                    String noMoviesInfo = "No movies were found";
                    textPane1.setText(noMoviesInfo);
                }

            }
            br.close();
            deletedBr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setVisibility(boolean b) {
        nameLabel.setVisible(b);
        textField1.setVisible(b);
        textPane1.setVisible(b);
        searchButton.setVisible(b);
        cancelButton.setVisible(b);
        moviesButton.setVisible(!b);
        seriesButton.setVisible(!b);
    }
}
