/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author someguy590
 */
public class MoveToOneFile {
    public static void main(String[] args) throws IOException {
        String sourcePath = "C:\\Users\\Some\\Documents\\Comics\\Punisher_MAX_1-22_Annual_Special_2008-2012\\";
        for (int i = 1; i <= 22; i++) {
            // Punisher MAX 001 (2010) (Digital) (Zone-Empire).cbr
            String issueName = "Punisher MAX 0" + (i < 10 ? "0" + i : i) + " (2010) (Digital) (Zone-Empire).cbr";
            File issue = new File(sourcePath + issueName);
            
            String targetName = "Punisher MAX 0" + (i < 10 ? "0" + i : i) + " (2010)";
            File target = new File(sourcePath + targetName);
            
            Files.move(issue.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
    
    public static void getToPage(File file) throws IOException {
        if (file.isFile())
            Files.move(file.toPath(), new File("C:\\Users\\Some\\Documents\\Comics\\Earth X\\All In One").toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        for (File page : file.listFiles())
            getToPage(page);
    }
}