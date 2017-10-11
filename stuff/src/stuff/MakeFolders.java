/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.io.File;

/**
 *
 * @author someguy590
 */
public class MakeFolders {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Some\\Documents\\Comics\\Punisher_MAX_1-22_Annual_Special_2008-2012\\";
        
        for (int i = 1; i <= 22; i++) {
            File folder = new File(filePath + "Punisher MAX 0" + (i < 10 ? "0" + i : i) + " (2010)");
            folder.mkdir();
        }
    }
}