package lab5;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task 1");
        List<Integer> list1 = List.of(2, 4, 6);
        System.out.println(Tasks.task1(list1));

        System.out.println("Task 2");
        List<String> list2 = List.of("TeST", "TEST", "test");
        System.out.println(Tasks.task2(list2));

        System.out.println("Task 3");
        List<Integer> list3 = List.of(1, 2, 2, 3, 1);
        System.out.println(Tasks.task3(list3));

        System.out.println("Task 4");
        List<String> list4 = List.of("cola", "dog", "cat");
        System.out.println(Tasks.task4(list4, "a"));

        System.out.println("Task 5");
        List<Integer> list5 = List.of(32, 1, 2, 344);
        List<String> exceptList = new ArrayList<>();
        try {
            System.out.println(Tasks.task5(list5));
            //System.out.println(Tasks.task5(exceptList));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        //System.out.println("Task 6");
        //int[] arrayFirst = {1, 2, 3, 4, 5};
        //System.out.println(Tasks.task6(arrayFirst));
        //arrayFirst = new int[]{1, 3, 5};
        //System.out.println(Tasks.task6(arrayFirst));

        System.out.println("Task 7");
        List<String> list7 = List.of("tea", "people", "Java", "NUMA");
        System.out.println(Tasks.task7(list7));
    }
}