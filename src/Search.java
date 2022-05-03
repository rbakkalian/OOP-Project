import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
                String x = textField1.getText();
                String info = "";
                String line = "" ;
                String delimiter = ",";

                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader("dataBase.csv"));
                    while ((line = br.readLine()) != null) {
                        if (line.startsWith(x)) {
                                if (x.length() == 0) {
                                    String noTextInfo = "Please enter a keyword for a lookup";
                                    textArea1.setText(noTextInfo);
                                } else {
                                    String[] filmData = line.split(delimiter);    // use comma as separator
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
                    br.close();
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
