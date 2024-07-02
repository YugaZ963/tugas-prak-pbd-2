/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.main;

import src.main.java.controller.PasswordManagerController;
import src.main.java.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Buat instance dari PasswordManagerController
        PasswordManagerController controller = new PasswordManagerController();
        
        // Berikan controller sebagai argumen ke konstruktor MainFrame
        MainFrame mainFrame = new MainFrame(controller);
        
        // Tampilkan GUI
        mainFrame.setVisible(true);
    }
}

