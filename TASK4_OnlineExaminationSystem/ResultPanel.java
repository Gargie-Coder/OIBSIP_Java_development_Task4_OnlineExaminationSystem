import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultPanel extends JPanel {
    private ExamApp app;
    private JLabel scoreLabel = new JLabel();
    private JLabel timeLabel = new JLabel();
    private JLabel statsLabel = new JLabel();
    private JTextArea detailsArea = new JTextArea();

    public ResultPanel(ExamApp app) {
        this.app = app;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        statsLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        top.add(scoreLabel);
        top.add(Box.createVerticalStrut(5));
        top.add(timeLabel);
        top.add(Box.createVerticalStrut(5));
        top.add(statsLabel);
        add(top, BorderLayout.NORTH);

        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        JButton logoutBtn = new JButton("Logout");
        JPanel bottom = new JPanel();
        bottom.add(logoutBtn);
        add(bottom, BorderLayout.SOUTH);

        logoutBtn.addActionListener(e -> app.showLogin());
    }

    public void showResult(List<Question> questions, int[] answers, int secondsTaken) {
        int correct = 0;
        int wrong = 0;
        int skipped = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            int given = answers[i];
            String right = q.getOptions()[q.getCorrectIndex()];

            if (given == -1) {
                skipped++;
                sb.append("Q" + (i + 1) + ": Not answered | Correct answer: " + right + "\n");
            } else if (given == q.getCorrectIndex()) {
                correct++;
                sb.append("Q" + (i + 1) + ": Correct\n");
            } else {
                wrong++;
                sb.append("Q" + (i + 1) + ": Wrong | Your answer: " + q.getOptions()[given]
                        + " | Correct answer: " + right + "\n");
            }
        }

        scoreLabel.setText("Score: " + correct + " out of " + questions.size());
        timeLabel.setText(String.format("Time taken: %02d:%02d", secondsTaken / 60, secondsTaken % 60));
        statsLabel.setText("Correct: " + correct + "   Incorrect: " + wrong + "   Not answered: " + skipped);
        detailsArea.setText(sb.toString());
        detailsArea.setCaretPosition(0);
    }
}