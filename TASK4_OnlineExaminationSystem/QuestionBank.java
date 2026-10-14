import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    public static List<Question> getQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"implements", "extends", "inherits", "super"}, 1));

        list.add(new Question("What is the default value of an int instance variable?",
                new String[]{"null", "0", "undefined", "-1"}, 1));

        list.add(new Question("Which collection does not allow duplicate elements?",
                new String[]{"ArrayList", "LinkedList", "HashSet", "Vector"}, 2));

        list.add(new Question("Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"}, 2));

        list.add(new Question("How many bytes does an int take in Java?",
                new String[]{"2", "4", "8", "Depends on the platform"}, 1));

        list.add(new Question("Which of these is NOT a primitive data type?",
                new String[]{"int", "boolean", "String", "char"}, 2));

        list.add(new Question("Which keyword prevents a method from being overridden?",
                new String[]{"static", "final", "abstract", "private"}, 1));

        list.add(new Question("Which exception is thrown when an integer is divided by zero?",
                new String[]{"NullPointerException", "ArithmeticException",
                        "NumberFormatException", "IOException"}, 1));

        list.add(new Question("Which interface does ArrayList implement?",
                new String[]{"List", "Map", "Set", "Queue"}, 0));

        list.add(new Question("What does JVM stand for?",
                new String[]{"Java Virtual Machine", "Java Variable Method",
                        "Joint Virtual Memory", "Java Verified Module"}, 0));

        return list;
    }
}