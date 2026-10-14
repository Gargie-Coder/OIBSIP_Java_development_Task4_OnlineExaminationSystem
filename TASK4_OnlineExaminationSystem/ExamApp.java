import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ExamApp extends JFrame {
    private CardLayout cards = new CardLayout();
    private JPanel container = new JPanel(cards);

    private User user = new User("student", "1234", "Student");
    private boolean examRunning = false;

    private LoginPanel loginPanel;
    private ProfilePanel profilePanel;
    private ExamPanel examPanel;
    private ResultPanel resultPanel;

    public ExamApp() {
        setTitle("Online Examination System");
        setSize(750, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        loginPanel = new LoginPanel(this);
        profilePanel = new ProfilePanel(this);
        examPanel = new ExamPanel(this);
        resultPanel = new ResultPanel(this);

        container.add(loginPanel, "login");
        container.add(profilePanel, "profile");
        container.add(examPanel, "exam");
        container.add(resultPanel, "result");
        add(container);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClose();
            }
        });

        showLogin();
    }

    public User getUser() {
        return user;
    }

    public void showLogin() {
        examRunning = false;
        loginPanel.clearFields();
        cards.show(container, "login");
    }

    public void showProfile() {
        profilePanel.refresh();
        cards.show(container, "profile");
    }

    public void startExam() {
        examRunning = true;
        examPanel.startExam();
        cards.show(container, "exam");
    }

    public void showResult(List<Question> questions, int[] answers, int secondsTaken) {
        examRunning = false;
        resultPanel.showResult(questions, answers, secondsTaken);
        cards.show(container, "result");
    }

    private void handleClose() {
        if (examRunning) {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to quit?",
                    "Quit Exam", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                examPanel.stopTimer();
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
}