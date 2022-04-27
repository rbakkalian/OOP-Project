import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Form1 extends JFrame{
    private JButton addButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton showAllButton;
    private JButton editButton;
    private JTextArea textArea1;
    private JPanel Form1;

    public static ArrayList<Movie> movies = new ArrayList<>();
    public static int mCount = 0;
    public static TvShow[] tvShows = new TvShow[100];
    public static int tvCount = 0;


    public Form1() {
        textArea1.setEditable(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Add add = new Add();
                add.setVisible(true);
                textArea1.setText("");
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit edit = new Edit();
                edit.setVisible(true);
                textArea1.setText("");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete delete = new Delete();
                delete.setVisible(true);
                textArea1.setText("");
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search search = new Search();
                search.setVisible(true);
                textArea1.setText("");
            }
        });
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Movie movie: movies)
                    textArea1.append(movie.returnStringInfo());
            }
        });
    }

    public static void main(String[] args) {
        Form1 form1 = new Form1();
        form1.setContentPane(form1.Form1);
        form1.setTitle("Movies Encyclopedia");
//        form1.setSize(600, 600);
        form1.setBounds(600, 200, 600, 600);
        form1.setVisible(true);
        form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
