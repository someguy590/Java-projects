/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author someguy590
 */
public class SlotMachineTest {
    
    public SlotMachineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testCreation() {
        // TODO review the generated test code and remove the default call to fail.
        SlotMachine s = new SlotMachine();
        
        assertEquals(0, s.getScore());
    }
    
    
}
