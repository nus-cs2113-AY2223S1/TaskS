package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

//        System.out.println("All data");
//        printData(tasksData);
//        printDeadlines(tasksData);
        printDeadlinesUsingStreams(tasksData);
//
//        System.out.println("Printing deadlines");
//        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines (using stream): " + countDeadlinesUsingStream(tasksData));

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
        System.out.println("Printing data using loop");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing data using streams");
        tasks.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing Deadlines using loops");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing Deadlines using streams");
        tasks.parallelStream()
                .filter(t -> t instanceof Deadline) //lambdas
                .forEach(System.out::println);
    }


}