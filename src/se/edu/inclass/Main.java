package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();
        System.out.println("All data:");
        //    printData(tasksData);
            printDeadlines(tasksData);
          printDeadlinesUsingStream(tasksData);

 /*       System.out.println("Printing deadlines");
        printDeadlines(tasksData);*/
        ArrayList<Task> filteredList = filterTaskByString(tasksData, "11");
        

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

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

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    public static ArrayList<Task> filterTaskByString(ArrayList<Task> tasks, String filteredList) {
        System.out.println("Printing sorted deadlines");
        tasks.stream().
                filter(t -> t.getDescription().contains(filteredList))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }


    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Sorted deadlines");
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))
                .forEach(System.out::println);
    }
}