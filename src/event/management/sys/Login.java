package event.management.sys;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton login, cancel;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(40, 20, 100, 30);
        add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(150, 20, 150, 30);
        add(usernameField);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(40, 70, 100, 30);
        add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 30);
        add(passwordField);

        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 140, 120, 30);
        cancel.addActionListener(this);
        add(cancel);

        setSize(350, 230);
        setLocation(600, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                conn c = new conn(); // Ensure your connection class is correctly implemented
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement pstmt = c.c.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    setVisible(false);
                    new Home(); // Ensure Home class is adapted for event management system
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
