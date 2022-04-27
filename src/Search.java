import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                int index;
                for (Movie movie : Form1.movies) {
                    if (movie.getName().equals(x)) {
                        index = Form1.movies.indexOf(movie);
                        m = Form1.movies.get(index);
                        textArea1.setText(m.returnStringInfo());
                        found = true;
                        break;
                    }
                }

                if (!found){
                    JOptionPane.showMessageDialog(Search, "Movie not found!");
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
