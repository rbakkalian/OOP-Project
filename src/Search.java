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
                /*int index;
                for (Movie movie : Form1.movies) {
                    if (movie.getName().equals(x)) {
                        index = Form1.movies.indexOf(movie);
                        m = Form1.movies.get(index);
                        textArea1.setText(m.returnStringInfo());
                        found = true;
                        break;
                    }
                }*/
                String line = "" ;
                String delimiter = ",";

                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader("dataBase.csv"));
                    while ((line = br.readLine()) != null) {
                        if (line.startsWith(x)) {

                            String[] filmData = line.split(delimiter);    // use comma as separator
                            String info  = "Name: " + filmData[0] + "\n" +
                                    "Genre: " + filmData[1] + "\n" +
                                    "Release Date: " + filmData[2] + "\n" +
                                    "Cast: " + filmData[3] + "\n" +
                                    "Age: " + filmData[4] + "\n" ;

                            textArea1.setText(info);
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
