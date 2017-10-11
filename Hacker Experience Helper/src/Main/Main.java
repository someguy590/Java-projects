package Main;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by someguy590 on 5/30/2016.
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HackerExperienceHelper h = new HackerExperienceHelper();

        Pattern p = Pattern.compile("a");
        Matcher m = p.matcher("a");

        // System.out.println(m.find());
        System.out.println(m.group());
    }



}
