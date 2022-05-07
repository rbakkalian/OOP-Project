import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.time.Instant;

public class Edit extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton editButton;
    private JButton cancelButton;
    private JPanel Edit;

    public Edit() {
        setContentPane(Edit);
        setTitle("Edit Movie");
        setBounds(600, 200, 400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isActive = true;

                String filmName = textField1.getText();
                String filmGenre = textField2.getText();
                String releaseDate = textField3.getText();
                String filmCharacters = textField4.getText();
                String lineFromDb = "";
                String lineFromRemovedDb = "";
                long deletionDate = Long.MIN_VALUE;
                long additionDate = Long.MIN_VALUE;
                long now = Instant.now().toEpochMilli();
                String delimiter = ",";

                BufferedReader dbBufferedReader;
                BufferedReader deletedDbBufferedReader;

                try {
                    dbBufferedReader = new BufferedReader(new FileReader("database.csv"));
                    deletedDbBufferedReader = new BufferedReader(new FileReader("databaseDeleted.csv"));

                    while ((lineFromRemovedDb = deletedDbBufferedReader.readLine()) != null) {
                        String[] filmDataDeleted = lineFromRemovedDb.split(delimiter);
                        if (filmName.equals(filmDataDeleted[0])) {
                            deletionDate = Long.parseLong(filmDataDeleted[6]);
                        }
                    }

                    while ((lineFromDb = dbBufferedReader.readLine()) != null) {
                        String[] filmData = lineFromDb.split(delimiter);
                        if (filmName.equals(filmData[0])) {
                            additionDate = Long.parseLong(filmData[5]);
                        }
                    }

                    if (deletionDate > additionDate) {
                        System.out.println("This Movie Has been Deleted ");
                    }


                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
