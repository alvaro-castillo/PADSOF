import javax.swing.*;


public class LoginPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel labelUsername = new JLabel("Username:");
    private JLabel labelPassword = new JLabel("Password:");
    private JLabel labelRegister = new JLabel("Don't have an account?");
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    public LoginPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(labelUsername);
        this.add(usernameField);
        this.add(labelPassword);
        this.add(passwordField);
        this.add(loginButton);
        this.add(labelRegister);
        this.add(registerButton);

    }



}