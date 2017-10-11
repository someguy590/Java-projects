/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.characters.to.typed.in.string;

/**
 *
 * @author someguy590
 */
public class AddCharactersToTypedInString {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String word = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345689";
        
        for (int i = 0; i < word.length(); i++) {
            System.out.print("\'" + word.charAt(i) + "\', ");
        }
    }
    
}