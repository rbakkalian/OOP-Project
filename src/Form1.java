import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//change genre to enum
public class Form1 extends JFrame {
    private JButton addButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton showAllButton;
    private JButton editButton;
    private JLabel title;
    private JPanel Form1;
    JToolBar toolBar;

    public static ArrayList<Movie> movies = new ArrayList<>();
    public static int mCount = 0;
    public static TvShow[] tvShows = new TvShow[100];
    public static int tvCount = 0;


    public Form1() {
        this.setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Add add = new Add();
                add.setVisible(true);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit edit = new Edit();
                edit.setVisible(true);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete delete = new Delete();
                delete.setVisible(true);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search search = new Search();
                search.setVisible(true);
            }
        });
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\download.jpg");
                for (Movie movie : movies) {
                    JLabel label = new JLabel(movie.returnStringInfo());
                    JLabel label1 = new JLabel();
                    label1.setIcon(image);
                    label1.setSize(100, 100);
                    infoPanel.add(label1);
                    infoPanel.add(label);
                    panel.add(infoPanel);
                    panel.revalidate();
                }
            }
        });

        this.setTitle("Movies");
        this.add(Form1);
        this.add(panel);
        panel.add(addButton);
        panel.add(editButton);
        panel.add(searchButton);
        panel.add(deleteButton);
        add(title, BorderLayout.PAGE_START);
        add(panel, BorderLayout.CENTER);
//        this.setSize(900, 900);
        this.setBounds(600, 200, 600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.pack();
        this.setVisible(true);
    }

    //public static void main(String[] args) {
//        Form1 form1 = new Form1();
//        form1.setContentPane(form1.Form1);
//        form1.setTitle("Movies Encyclopedia");
////        form1.setSize(600, 600);
//        form1.setBounds(600, 200, 600, 600);
//        form1.setVisible(true);
//
//        form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //new Form1();

    //}

}
