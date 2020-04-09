import javax.swing.*;


public class RegisterPanel extends JPanel{

    private static final long serialVersionUID=1L;
    private JLabel lableUsername = new JLabel("Username");
    private JLabel lableNationalID = new JLabel("National ID");
    private JLabel lablePassword = new JLabel("Password");
    private JTextField usernameField = new JTextField(20);
    private JTextField nationalIDField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton createAccount = new JButton("Create Account");

    public RegisterPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(lableUsername);
        this.add(usernameField);
        this.add(lableNationalID);
        this.add(nationalIDField);
        this.add(lablePassword);
        this.add(passwordField);
        this.add(Box.createRigidArea(new Dimension(0, 80)));
        this.add(createAccount);
    }
}