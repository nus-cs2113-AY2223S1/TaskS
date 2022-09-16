package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Event;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("All data: ");
        printDataUsingStream(tasksData);

        System.out.println("Printing deadlines");
        printDeadlinesUsingStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlinesUsingStream(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter(t -> t instanceof Deadline)
                .count();

        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using loop: ");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .forEach(System.out::println);
    }


    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Print deadlines using loops: ");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }

    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        tasks.parallelStream()
                .filter(n -> n instanceof Deadline)
                .forEach(System.out::println);
    }
}