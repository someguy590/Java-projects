
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author someguy590
 */
public class SlotMachine {
    private int[] slots = new int[3];
    private int score = 0;
    
    SlotMachine() {
        slots[0] = (int)(Math.random() * 10);
        slots[1] = (int)(Math.random() * 10);
        slots[2] = (int)(Math.random() * 10);
        score = 0;
    }
    
    public void play() {
        SlotMachine slotMachine = new SlotMachine();
        
        Random r = new Random();
        
        for (int i = 0; i < slots.length; i++) {
            slotMachine.slots[i] = r.nextInt() * 10;
        }
        
        
    }
    
    public void setScore() {
        
    }
    
    public int getScore() {
        return score;
    }
}
