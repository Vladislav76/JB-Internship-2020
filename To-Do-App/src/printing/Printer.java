package printing;

import model.task.Task;

import java.util.Collections;
import java.util.List;

public class Printer {

    public static String DELIMITER_STRING = "-";
    public static int DELIMITER_LINE_LENGTH = 100;

    public static void print(List<Task> tasks, int pageNumber, int pageCapacity, int pageAmount) {
        System.out.flush();

        for (Task task : tasks) print(task);
        printDelimiterLine();

        System.out.println("Page: " + pageNumber + "/" + pageAmount);
        System.out.println("Page capacity: " + pageCapacity);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printManual(String content) {
        System.out.println("\"TODO App\" manual");
        printDelimiterLine();
        System.out.println(content);
    }

    private static void printDelimiterLine() {
        String str = String.join("", Collections.nCopies(DELIMITER_LINE_LENGTH, DELIMITER_STRING));
        System.out.println(str);
    }

    private static void print(Task task) {
        System.out.println((task.isDone() ? "+ " : "- ") + task.getId() + ". " + task.getName());
    }
}
