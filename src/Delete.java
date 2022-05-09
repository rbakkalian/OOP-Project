import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.Instant;

public class Delete extends JFrame {
    private JTextField textField1;
    private JButton deleteButton;
    private JButton cancelButton;
    private JPanel Delete;
    private JLabel nameLabel;
    private JLabel titleLabel;
    private JButton moviesButton;
    private JButton seriesButton;
    private int count = 0;

    Movie m;

    public Delete() {
        String title = "Delete Content";
        setContentPane(Delete);
        setTitle("Delete");
        titleLabel.setText(title);
        Delete.setBorder(new EmptyBorder(10, 10, 10, 10));
        setBounds(600, 200, 400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisibility(false);

        moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleLabel.setText("Delete Movie");
                setVisibility(true);
                count = 0;
            }
        });

        seriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(true);
                titleLabel.setText("Delete TvShow");
                count = 1;
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisibility(false);
                titleLabel.setText(title);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0)
                    deleteMovie();
                else deleteTvShow();
            }
        });
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
            JOptionPane.showMessageDialog(Delete, response);
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
            JOptionPane.showMessageDialog(Delete, response);
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setVisibility(boolean b) {
        nameLabel.setVisible(b);
        textField1.setVisible(b);
        deleteButton.setVisible(b);
        cancelButton.setVisible(b);
        moviesButton.setVisible(!b);
        seriesButton.setVisible(!b);
    }
}
