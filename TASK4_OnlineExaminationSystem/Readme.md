# Online Examination System (Java Swing)

A GUI-based online examination system built with Java Swing. Students log in, update their profile, answer multiple-choice questions against a countdown timer, and see a detailed result at the end.

Built as Task 4 of the Oasis Infobyte Java Development internship.

## Features
- Login screen with username and password (access denied for invalid credentials)
- Profile update screen to change the display name and password before starting the exam
- One multiple-choice question at a time, with 4 radio-button options
- Next and Previous buttons; selected answers are remembered when you move between questions
- Countdown timer visible throughout the exam; the exam is submitted automatically when it reaches zero
- Manual submit button with a confirmation dialog
- Result screen showing the score (X out of Y), time taken, and a correct / incorrect / not answered breakdown, with the correct answer shown for each wrong or skipped question
- "Are you sure you want to quit?" dialog if the window is closed during an exam
- Logout button on the result screen that returns to the login screen

## Project Structure
| File | Purpose |
|------|---------|
| `Main.java` | Starts the application |
| `ExamApp.java` | Main window; switches between screens using CardLayout and handles the window-close dialog |
| `LoginPanel.java` | Login screen and credential validation |
| `ProfilePanel.java` | Screen to update display name and password |
| `ExamPanel.java` | Exam screen with question display, navigation, timer and submit |
| `ResultPanel.java` | Score, time taken and answer breakdown |
| `Question.java` | Stores a question, its 4 options and the correct option |
| `QuestionBank.java` | Holds the list of 10 Java questions |
| `User.java` | Stores username, password and display name |

## How to Run
Make sure Java (JDK 8 or above) is installed, then from this folder run:
```
javac *.java
java Main
```

## Demo Login
| Username | Password |
|----------|----------|
| student | 1234 |

## How It Works
- `CardLayout` switches between the login, profile, exam and result screens inside one window.
- `javax.swing.Timer` ticks every second to update the countdown and trigger auto-submit.
- Answers are stored in an `int[]` (-1 means not answered), so selections persist when navigating between questions.
- `ButtonGroup` with `JRadioButton` ensures only one option can be selected per question.

## Concepts Used
- Java Swing (JFrame, JPanel, JLabel, JButton, JRadioButton, JOptionPane)
- CardLayout, BorderLayout, GridBagLayout and BoxLayout
- Event handling with action listeners and a window listener
- Object-oriented design with separate classes for each screen and data type
- ArrayList for storing questions

## Notes
- The exam is 10 minutes long (set by `EXAM_SECONDS` in `ExamPanel.java`).
- Questions and the user account are stored in the code, not in a database.
- Profile changes last only for the current run of the program and reset when it is closed.
- Questions can be left unanswered; they are counted as "not answered" in the result.

## Author
Gargie