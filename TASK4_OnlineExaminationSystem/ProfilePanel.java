import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private ExamApp app;
    private JLabel welcomeLabel = new JLabel();
    private JTextField nameField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15);

    public ProfilePanel(ExamApp app) {
        this.app = app;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);

        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(welcomeLabel, c);

        c.gridwidth = 1;
        c.gridy = 1;
        add(new JLabel("Display name:"), c);
        c.gridx = 1;
        add(nameField, c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("New password:"), c);
        c.gridx = 1;
        add(passField, c);

        JLabel note = new JLabel("Leave the password blank to keep the current one.");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(note, c);

        JPanel buttons = new JPanel();
        JButton saveBtn = new JButton("Save Changes");
        JButton startBtn = new JButton("Start Exam");
        buttons.add(saveBtn);
        buttons.add(startBtn);
        c.gridy = 4;
        add(buttons, c);

        saveBtn.addActionListener(e -> saveProfile());
        startBtn.addActionListener(e -> app.startExam());
    }

    public void refresh() {
        welcomeLabel.setText("Welcome, " + app.getUser().getDisplayName());
        nameField.setText(app.getUser().getDisplayName());
        passField.setText("");
    }

    private void saveProfile() {
        String name = nameField.getText().trim();
        String newPass = new String(passField.getPassword());

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Display name cannot be empty.",
                    "Profile", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!newPass.isEmpty() && newPass.length() < 4) {
            JOptionPane.showMessageDialog(this, "Password must be at least 4 characters.",
                    "Profile", JOptionPane.WARNING_MESSAGE);
            return;
        }

        app.getUser().setDisplayName(name);
        if (!newPass.isEmpty()) {
            app.getUser().setPassword(newPass);
        }
        JOptionPane.showMessageDialog(this, "Profile updated.");
        refresh();
    }
}