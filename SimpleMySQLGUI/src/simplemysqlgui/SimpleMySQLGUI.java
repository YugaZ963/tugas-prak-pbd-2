import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SimpleMySQLGUI extends JFrame {
    private Connection connection;
    private JTextField nameField, ageField;
    private JButton insertButton;

    public SimpleMySQLGUI() {
        super("Simple MySQL GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();

        insertButton = new JButton("Insert Data");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(insertButton);

        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            // Ganti parameter koneksi sesuai dengan konfigurasi MySQL Anda
            String url = "jdbc:mysql://localhost:3306/simple-gui";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL database");
        } catch (SQLException e) {
            System.out.println("Connection to MySQL database failed!");
            e.printStackTrace();
        }
    }

    private void insertData() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());

        try {
            String sql = "INSERT INTO identity (name, age) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, age);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to insert data into MySQL database!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleMySQLGUI().setVisible(true);
            }
        });
    }
}
