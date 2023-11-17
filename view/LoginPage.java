package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame{
    public LoginPage(){
        JFrame frame = this;
        JPanel panel = new JPanel();
        setTitle("Menu Login");
        setSize(300, 200);
        
        JLabel emailLabel = new JLabel("Masukkan E-Mail:");
        JTextField emailField = new JTextField();
        panel.add(emailLabel); panel.add(emailField);
        JLabel passwordLabel = new JLabel("Masukkan Password:");
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordLabel); panel.add(passwordField);
        
        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (controller.SystemFunction.checkEmailPass(emailField.getText(), String.valueOf(passwordField.getPassword()))){
                    frame.dispose();
                    new GameListPage(controller.SystemFunction.getUserByEmail(emailField.getText()));
                }
                else{
                    JOptionPane.showMessageDialog(null, "Gagal Login!", "Failed!", JOptionPane.ERROR_MESSAGE, null);
                }
            }
        });
        
        panel.add(login);
        panel.setLayout(new GridLayout(4, 2));
        add(panel);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new LoginPage();
    }
}