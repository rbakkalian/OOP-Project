import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Search extends JFrame {
    private JTextField textField1;
    private JButton searchButton;
    private JButton cancelButton;
    private JTextArea textArea1;
    private JPanel Search;
    private JButton editButton;

    Movie m;
    boolean found = false;

    public Search() {
        setContentPane(Search);
        setTitle("Search Movie");
        setBounds(600, 200, 400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit edit = new Edit();
                edit.setVisible(true);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isDeleted = false;
                String x = textField1.getText();
                String info = "";
                String lineFromDb = "" ;
                String lineFromRemovedDb = "" ;
                long deletionDate = 0;
                long additionDate = 0;
                String delimiter = ",";

                BufferedReader dbBufferedReader;
                BufferedReader deletedDbBufferedReader;
                try {
                    dbBufferedReader = new BufferedReader(new FileReader("dataBase.csv"));
                    deletedDbBufferedReader = new BufferedReader(new FileReader("dataBaseDeleted.csv"));
                    while ((lineFromRemovedDb = deletedDbBufferedReader.readLine()) != null) {
                        String[] filmDataDeleted = lineFromRemovedDb.split(delimiter);
                        if (x.equals(filmDataDeleted[0])) {
                            deletionDate = Long.parseLong(filmDataDeleted[6]);
                            isDeleted = true;
                        }
                    }
                    while ((lineFromDb = dbBufferedReader.readLine()) != null) {
                        String[] filmData = lineFromDb.split(delimiter);
                        additionDate = Long.parseLong(filmData[5]);
                        if (x.equals(filmData[0]) && (!isDeleted || additionDate > deletionDate) ){
                                if (x.length() == 0) {
                                    String noTextInfo = "Please enter a keyword for a lookup";
                                    textArea1.setText(noTextInfo);
                                } else {
                                       // use comma as separator
                                    info  = "Name: " + filmData[0] + "\n" +
                                            "Genre: " + filmData[1] + "\n" +
                                            "Release Date: " + filmData[2] + "\n" +
                                            "Cast: " + filmData[3] + "\n" +
                                            "Age: " + filmData[4] + "\n" ;
                                    textArea1.setText(info);
                                    break;
                                }
                        } else {
                            String noMoviesInfo = "No movies were found";
                            textArea1.setText(noMoviesInfo);
                        }

                    }
                    dbBufferedReader.close();
                    deletedDbBufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                textField1.setText("");
            }
        });
    }
}
