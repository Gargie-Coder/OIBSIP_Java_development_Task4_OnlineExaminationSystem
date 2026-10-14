import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private ExamApp app;
    private JTextField userField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15);

    public LoginPanel(ExamApp app) {
        this.app = app;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("Online Examination System");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(title, c);

        c.gridwidth = 1;
        c.gridy = 1;
        add(new JLabel("Username:"), c);
        c.gridx = 1;
        add(userField, c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Password:"), c);
        c.gridx = 1;
        add(passField, c);

        JButton loginBtn = new JButton("Login");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(loginBtn, c);

        JLabel hint = new JLabel("Demo login: student / 1234");
        c.gridy = 4;
        add(hint, c);

        loginBtn.addActionListener(e -> doLogin());
    }

    private void doLogin() {
        String u = userField.getText().trim();
        String p = new String(passField.getPassword());

        if (u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.",
                    "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (app.getUser().checkLogin(u, p)) {
            app.showProfile();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.",
                    "Access Denied", JOptionPane.ERROR_MESSAGE);
            passField.setText("");
        }
    }

    public void clearFields() {
        userField.setText("");
        passField.setText("");
    }
}
