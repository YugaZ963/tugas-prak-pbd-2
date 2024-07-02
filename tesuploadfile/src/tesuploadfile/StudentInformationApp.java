/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesuploadfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author WIN11
 */
public class StudentInformationApp {

    private JFrame frame;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField tfSearch;
    private JComboBox<String> cbFilter;

    private ArrayList<Student> studentList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentInformationApp().initialize();
            }
        });
    }

    public void initialize() {
        // Initialize student list
        studentList = new ArrayList<>();

        // Create the main frame
        frame = new JFrame("Student Information App");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JPanel panelTop = new JPanel();
        JLabel lblSearch = new JLabel("Search:");
        tfSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        String[] filterOptions = {"Name", "Student ID", "Program"};
        cbFilter = new JComboBox<>(filterOptions);
        JButton btnUpload = new JButton("Upload Document");

        // Table to display student data
        String[] columnNames = {"Name", "Student ID", "Program"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Layout
        panelTop.add(lblSearch);
        panelTop.add(tfSearch);
        panelTop.add(cbFilter);
        panelTop.add(btnSearch);
        panelTop.add(btnUpload);

        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action Listeners
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        btnUpload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadDocument();
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    private void searchStudent() {
        String searchText = tfSearch.getText().trim();
        String filter = (String) cbFilter.getSelectedItem();
        ArrayList<Student> filteredStudents = new ArrayList<>();

        if (searchText.isEmpty()) {
            filteredStudents.addAll(studentList); // show all students if search is empty
        } else {
            // Perform filtering based on selected filter option
            for (Student student : studentList) {
                if (filter.equals("Name") && student.getName().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredStudents.add(student);
                } else if (filter.equals("Student ID") && student.getStudentID().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredStudents.add(student);
                } else if (filter.equals("Program") && student.getProgram().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredStudents.add(student);
                }
            }
        }

        // Update table with filtered data
        updateTable(filteredStudents);
    }

    private void uploadDocument() {
        // Create file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file to upload");

        int userSelection = fileChooser.showOpenDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Placeholder for further logic (e.g., save document information with student data)
            // In a real application, you would associate the uploaded file with a specific student
            
            JOptionPane.showMessageDialog(frame, "File selected: " + selectedFile.getName());
        }
    }

    private void updateTable(ArrayList<Student> students) {
        // Clear current table data
        tableModel.setRowCount(0);

        // Add filtered students to table
        for (Student student : students) {
            Object[] row = {student.getName(), student.getStudentID(), student.getProgram()};
            tableModel.addRow(row);
        }
    }

    // Sample Student class
    private class Student {
        private String name;
        private String studentID;
        private String program;

        public Student(String name, String studentID, String program) {
            this.name = name;
            this.studentID = studentID;
            this.program = program;
        }

        public String getName() {
            return name;
        }

        public String getStudentID() {
            return studentID;
        }

        public String getProgram() {
            return program;
        }
    }
}