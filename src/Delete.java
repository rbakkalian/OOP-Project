import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.Instant;

public class Delete extends JFrame {
    private JTextField textField1;
    private JButton deleteButton;
    private JButton cancelButton;
    private JPanel Delete;

    Movie m;

    public Delete() {
        setContentPane(Delete);
        setTitle("Delete Movie");
        setBounds(600, 200, 400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                                printWriter = new PrintWriter(new BufferedWriter(new FileWriter("dataBaseDeleted.csv",true)));
                                StringBuilder builder  = new StringBuilder();
                                builder.append(filmData[0]+",");
                                builder.append(filmData[1]+",");
                                builder.append(filmData[2]+",");
                                builder.append(filmData[3]+",");
                                builder.append(filmData[4]+",");
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
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
