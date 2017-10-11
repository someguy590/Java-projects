package chapter24;

import java.util.ArrayList;

public class Exercise24_02 {
    public static void main(String[] args) {
        new Exercise24_02();
    }

    public Exercise24_02() {
        String[] name = {"Tom", "George", "Peter", "Jean", "George", "Jane"};
        MyList<String> list = new MyLinkedList<>(name);
        
        System.out.println(list.contains("George"));
        System.out.println(list.get(3));
        System.out.println(list.indexOf("George"));
        System.out.println(list.lastIndexOf("George"));
        list.set(4, "Michael");
        
        System.out.println(list);
        
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        
        
        
    }
}