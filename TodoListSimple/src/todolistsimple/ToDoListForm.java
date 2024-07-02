/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistsimple;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoListForm extends JFrame {
    private JTextField txtTask;
    private JButton btnAdd;
    private JButton btnDelete;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;

    public ToDoListForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("ToDo List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        // Initialize components
        txtTask = new JTextField();
        btnAdd = new JButton("Add Task");
        btnDelete = new JButton("Delete Task");
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        scrollPane = new JScrollPane(taskList);

        // Set bounds
        txtTask.setBounds(20, 20, 240, 25);
        btnAdd.setBounds(270, 20, 100, 25);
        scrollPane.setBounds(20, 60, 350, 150);
        btnDelete.setBounds(150, 220, 100, 25);

        // Add components to frame
        add(txtTask);
        add(btnAdd);
        add(scrollPane);
        add(btnDelete);

        // Add action listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = txtTask.getText();
                if (!task.isEmpty()) {
                    listModel.addElement(task);
                    txtTask.setText("");
                } else {
                    JOptionPane.showMessageDialog(ToDoListForm.this, "Task cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(ToDoListForm.this, "Select a task to delete", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoListForm().setVisible(true);
        });
    }
}

