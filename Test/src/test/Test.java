package test;

public class Test {
        public static String s1 = "sup";
        public static String s2 = "yo";
        public static String s3 = "sup";
    public static void main(String[] args) {
        swap(s1, s2);
        
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
    }
    
    public static void swap(String str1, String str2) {
        String temp = str1;
        str1 = str2;
        str2 = temp;
        
        System.out.println("temp: " + temp);
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
    }
}
