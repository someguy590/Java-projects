/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mole.game;

import java.util.Random;

public class Mole {

    private int randomUpdateCounter;
    private int resetCounter;
    private int xCoord;
    private int yCoord;
    
    private MoleState moleState;

    public enum MoleState {

        Up("O"),
        Down("X");

        private String val;

        private MoleState(String state) {
            val = state;
        }

        @Override
        public String toString() {
            return val;
        }
    }
    
    private int getXCoord() {
        return xCoord;
    }
    
    private int getYCoord() {
        return yCoord;
    }
    
    public Mole(Random seed) {   //generate a random mole on a 10x10 grid
        xCoord = seed.nextInt(10);
        yCoord = seed.nextInt(10);
        randomUpdateCounter = seed.nextInt(50) + 1; //so it doesn't randomly roll a 0
        //pick a number depending on how often it refreshes, 50 might be far too low
        //maybe go like 200, 500 or something...
        resetCounter = 0;
        moleState = MoleState.Up;
    }
    
    public Mole(int x, int y) {  //generate a mole on a set coordinate on the grid
        xCoord = x;
        yCoord = y;
        resetCounter = 0;
        moleState = MoleState.Up;

                    //set your random variables here with a new Random object
        //xCoord
        //yCoord
        //randomUpdateCounter
    }
    
    public String display() {
                    //or void?  Or do you need events?
        //Not sure on your context, or if this is supposed to draw directly on grid based on objects/event handlers?
        //Whatever you need to pass back to the grid to have it display would go in this function
        return moleState.toString();
    }
    
    public void update() { //from your grid/activity class you would call this on each mole object every frame
        resetCounter++;
        if (resetCounter < randomUpdateCounter) {
            return;
        }
        resetMole();
    }
    
    public void hit() {
        resetMole();
    }
    
    private void resetMole() {
        //mole state should be set to the opposite of what it was
        Random seed = new Random(); //initialize the random object
        resetCounter = 0;
        randomUpdateCounter = seed.nextInt(50) + 1;
        xCoord = seed.nextInt(10);
        yCoord = seed.nextInt(10);
    }
}
