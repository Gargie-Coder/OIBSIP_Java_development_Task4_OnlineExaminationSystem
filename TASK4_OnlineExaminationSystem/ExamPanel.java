import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ExamPanel extends JPanel {
    private static final int EXAM_SECONDS = 10 * 60; // change this to 30 * 60 for 30 minutes

    private ExamApp app;
    private List<Question> questions;
    private int[] answers;
    private int current;
    private int secondsLeft;
    private javax.swing.Timer timer;

    private JLabel candidateLabel = new JLabel();
    private JLabel timerLabel = new JLabel();
    private JLabel progressLabel = new JLabel();
    private JTextArea questionArea = new JTextArea(3, 40);
    private JRadioButton[] optionButtons = new JRadioButton[4];
    private ButtonGroup group = new ButtonGroup();
    private JButton prevBtn = new JButton("Previous");
    private JButton nextBtn = new JButton("Next");
    private JButton submitBtn = new JButton("Submit");

    public ExamPanel(ExamApp app) {
        this.app = app;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // top bar: candidate name on the left, timer on the right
        JPanel top = new JPanel(new BorderLayout());
        candidateLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        top.add(candidateLabel, BorderLayout.WEST);
        top.add(timerLabel, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // centre: question and options
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        progressLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        center.add(progressLabel);
        center.add(Box.createVerticalStrut(10));

        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setOpaque(false);
        questionArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        questionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        center.add(questionArea);
        center.add(Box.createVerticalStrut(15));

        for (int i = 0; i < 4; i++) {
            final int index = i;
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 15));
            optionButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            optionButtons[i].addActionListener(e -> answers[current] = index);
            group.add(optionButtons[i]);
            center.add(optionButtons[i]);
            center.add(Box.createVerticalStrut(8));
        }
        add(center, BorderLayout.CENTER);

        // bottom: navigation and submit
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        bottom.add(prevBtn);
        bottom.add(nextBtn);
        bottom.add(submitBtn);
        add(bottom, BorderLayout.SOUTH);

        prevBtn.addActionListener(e -> {
            current--;
            loadQuestion();
        });
        nextBtn.addActionListener(e -> {
            current++;
            loadQuestion();
        });
        submitBtn.addActionListener(e -> confirmSubmit());
    }

    public void startExam() {
        questions = QuestionBank.getQuestions();
        answers = new int[questions.size()];
        Arrays.fill(answers, -1); // -1 means not answered
        current = 0;
        secondsLeft = EXAM_SECONDS;

        candidateLabel.setText("Candidate: " + app.getUser().getDisplayName());
        updateTimerLabel();
        loadQuestion();

        stopTimer();
        timer = new javax.swing.Timer(1000, e -> tick());
        timer.start();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void tick() {
        secondsLeft--;
        updateTimerLabel();
        if (secondsLeft <= 0) {
            stopTimer();
            JOptionPane.showMessageDialog(this, "Time is up! Your exam will be submitted now.");
            finishExam();
        }
    }

    private void updateTimerLabel() {
        timerLabel.setText(String.format("Time Left: %02d:%02d", secondsLeft / 60, secondsLeft % 60));
    }

    private void loadQuestion() {
        Question q = questions.get(current);
        progressLabel.setText("Question " + (current + 1) + " of " + questions.size());
        questionArea.setText(q.getText());

        String[] opts = q.getOptions();
        group.clearSelection();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(opts[i]);
        }
        if (answers[current] != -1) {
            optionButtons[answers[current]].setSelected(true);
        }

        prevBtn.setEnabled(current > 0);
        nextBtn.setEnabled(current < questions.size() - 1);
    }

    private void confirmSubmit() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you want to submit the exam now?",
                "Confirm Submit", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            finishExam();
        }
    }

    private void finishExam() {
        stopTimer();
        int secondsTaken = EXAM_SECONDS - secondsLeft;
        app.showResult(questions, answers, secondsTaken);
    }
}