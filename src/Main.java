import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private static final String TVSHOW_FILE_NAME = "tvDataBase.csv";
    private static final String MOVIE_FILE_NAME = "dataBase.csv";
    private static final String DELETED_FILE_NAME = "dataBaseDeleted.csv";
    private JMenuBar menuBar = new JMenuBar();
    public static ArrayList<Movie> movies = new ArrayList<>();
    public static int mCount = 0;
    public static ArrayList<TvShow> tvShows = new ArrayList<>();
    public static int tvCount = 0;
    int count = 0;
    int count1 = 0;

    public Main() {
        JPanel mainPanel = new JPanel();
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);

        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Help");
        menuBar.add(menu);

        JToolBar toolBarTop = new JToolBar();
        this.add(toolBarTop, BorderLayout.PAGE_START);

        Icon icon = new ImageIcon("src/assets/plus-icon-13062.png");
        JButton addButton = new JButton();
        addButton.setIcon(icon);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Add add = new Add();
                add.setVisible(true);
            }
        });

        Icon icon1 = new ImageIcon("src/assets/edit-icon-png-3584.png");
        JButton editButton = new JButton();
        editButton.setIcon(icon1);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit edit = new Edit();
                edit.setVisible(true);
            }
        });

        Icon icon2 = new ImageIcon("src/assets/search-icon-png-9978.png");
        JButton searchButton = new JButton();
        searchButton.setIcon(icon2);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search search = new Search();
                search.setVisible(true);
            }
        });

        Icon icon3 = new ImageIcon("src/assets/trash-can-icon-28688 (1).png");
        JButton deleteButton = new JButton();
        deleteButton.setIcon(icon3);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete delete = new Delete();
                delete.setVisible(true);
            }
        });

        toolBarTop.add(addButton);
        toolBarTop.add(editButton);
        toolBarTop.add(searchButton);
        toolBarTop.add(deleteButton);

        JToolBar toolBarBottom = new JToolBar();
        toolBarBottom.setLayout(new FlowLayout());
        toolBarBottom.setAlignmentX(FlowLayout.CENTER);
        this.add(toolBarBottom, BorderLayout.PAGE_END);

        int movieLines = getFileLines(MOVIE_FILE_NAME);

        int tvShowLines = getFileLines(TVSHOW_FILE_NAME);

        JPanel moviesPanel = new JPanel();
        JPanel seriesPanel = new JPanel();
        JPanel[] moviesInfoPanel = new JPanel[movieLines * 2];
        JPanel[] seriesInfoPanel = new JPanel[tvShowLines * 2];

        JPanel containerPanel = new JPanel();
        containerPanel.setAlignmentX(FlowLayout.LEFT);
        containerPanel.setPreferredSize(new Dimension(1600, 1600));
        JScrollPane scrollFrame = new JScrollPane(containerPanel);
        containerPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(1000, 1000));

        Checkbox movieCheckbox = new Checkbox("Movies", false);
        movieCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    moviesPanel.setVisible(true);
                    moviesPanel.setLayout(new GridLayout(movieLines / 2, 2, 5, 5));
                    moviesPanel.setBackground(Color.cyan);

                    showAll(moviesInfoPanel, moviesPanel, MOVIE_FILE_NAME, DELETED_FILE_NAME);
                } else {
                    moviesPanel.setVisible(false);
                    moviesPanel.removeAll();
                }
            }
        });

        Checkbox tvShowCheckbox = new Checkbox("Tv Shows", false);
        tvShowCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    seriesPanel.setVisible(true);
                    seriesPanel.setLayout(new GridLayout(tvShowLines / 2, 2, 5, 5));
                    seriesPanel.setBackground(Color.cyan);

                    showAll(seriesInfoPanel, seriesPanel, TVSHOW_FILE_NAME, DELETED_FILE_NAME);
                } else {
                    seriesPanel.setVisible(false);
                    seriesPanel.removeAll();
                }
            }
        });

        toolBarBottom.add(movieCheckbox);
        toolBarBottom.add(tvShowCheckbox);

        containerPanel.add(moviesPanel);
        containerPanel.add(seriesPanel);

        this.add(scrollFrame);

        setTitle("Movies");
        setBounds(600, 200, 1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showAll(JPanel[] mainPanel, JPanel subPanel, String fileName, String deletedFileName) {
        boolean isDeleted = false;
        String line = "";
        String lineFromRemovedDb = "";
        long deletionDate = Long.MIN_VALUE;
        String delimiter = ",";
        String filmData[] = new String[10];

        BufferedReader br;
        BufferedReader deletedBr;
        try {
            br = new BufferedReader(new FileReader(fileName));
            deletedBr = new BufferedReader(new FileReader(deletedFileName));
            int i = 0;


            while ((line = br.readLine()) != null) {
                filmData = line.split(delimiter);    // use comma as separator
                String filmName = filmData[0];

                while ((lineFromRemovedDb = deletedBr.readLine()) != null) {
                    String[] filmDataDeleted = lineFromRemovedDb.split(delimiter);
                    if (filmName.equals(filmDataDeleted[0])) {
                        deletionDate = Long.parseLong(filmDataDeleted[7]);
                        isDeleted = true;


                    }

                }

            }
            if (!isDeleted || Long.parseLong(filmData[6]) > deletionDate)  {
                ImageIcon imageIcon = new ImageIcon(filmData[5]);
                JPanel infoP = new JPanel(new BorderLayout());
                JButton button = new JButton();
                button.setLayout(new BorderLayout());
                button.setIcon(imageIcon);
                button.setText(filmData[0]);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                infoP.add(button);
                mainPanel[i] = new JPanel();
                mainPanel[i].add(infoP);
                mainPanel[i].setBackground(Color.yellow);
                subPanel.add(mainPanel[i]);
                subPanel.revalidate();
            }

            br.close();
            deletedBr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }}

    private int getFileLines(String fileName) {
        int numOfLines = 0;
        try {
            File file = new File(fileName);
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
            lineNumberReader.skip(Long.MAX_VALUE);
            numOfLines = lineNumberReader.getLineNumber();
            lineNumberReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return numOfLines;
    }

    public static void main(String[] args) {
        new Main();
    }
}
