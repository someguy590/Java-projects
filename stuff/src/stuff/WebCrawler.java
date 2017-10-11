/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Scanner;

public class WebCrawler {
    public static void main(String[] args) {
        String[] keyWords = {"Flash", "Convergence", "Quinn", "Storm",
            "Deadpool", "Punisher", "Spider-Man", "X-Men", "Spider-man",
            "x-men", "Iron", "Loki", "Girl", "Daredevil", "Wolverine",
            "Endgame", "Kombat", "Secret", "Gwen", "Injustice", "Howard",
            "Widow"};
        
        String sourceFile = "C:\\Users\\Some\\Desktop\\source.txt";
        String toFile = "C:\\Users\\Some\\Desktop\\titles.txt";
        
        scrape("http://zippyshare.com/Empirestash/c4i7sts4/dir.html#", keyWords, toFile);
    }

    public static void scrape(String urlString, String[] keyWords, String toFile) {
        final String ANCHOR_ELEMENT_PATTERN = "<a class=\"name\"";
        
        try {
            java.net.URL url = new java.net.URL(urlString);
            Scanner input = new Scanner(url.openStream());
            PrintWriter output = new PrintWriter(new FileOutputStream(toFile, true));
            while (input.hasNext()) {
                String line = input.nextLine();
                // Check if line contains hyperlink
                if (line.contains(ANCHOR_ELEMENT_PATTERN)) {
                    // Check if title matches keywords
                    System.out.println(line);
                    if (containsKeyWords(line, keyWords)) {
                        // Write link and issue title to file
                        output.println(line + "<br></br>");
                    }
                }
            }
            
            input.close();
            output.close();
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static boolean containsKeyWords(String line, String[] words) {
        for (String s : words) {
            if (line.contains(s)) {
                return true;
            }
        }

        return false;
    }

}
