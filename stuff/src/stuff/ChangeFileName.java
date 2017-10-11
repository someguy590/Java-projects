/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author someguy590
 */
public class ChangeFileName {
    public static void main(String[] args) {
        // Absolute file path
        String absFilePath = "C:\\Users\\Some\\Downloads";
        
        File file = new File(absFilePath);
        
        replaceString(file, "_", " ");
    }
    
    public static void replaceString(File file, String oldStr, String newStr) {
        if (file.isDirectory())
            for (File f : file.listFiles())
                replaceString(f, oldStr, newStr);
        
        String fileName = file.getName();
        String newfileName = fileName.replaceAll(oldStr, newStr);
        
        try {
            Files.move(file.toPath(), file.toPath().resolveSibling(newfileName));
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
}