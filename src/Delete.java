import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Iterator;

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
                String x = textField1.getText();

                //To avoid the throw of ConcurrentModificationExceptionError we used iterators to keep the count of the list balanced
                for (Iterator<Movie> itr = Form1.movies.iterator(); itr.hasNext(); ) {
                    m = itr.next();
                    if (m.getName().equals(x)) {
                        JOptionPane.showMessageDialog(Delete, "Movie " + x + " deleted successfully!");
                        itr.remove();
                    }
                    else {
                        JOptionPane.showMessageDialog(Delete, "Movie not found!");
                        break;
                    }
                    textField1.setText("");
                }
            }
        });
    }
}
