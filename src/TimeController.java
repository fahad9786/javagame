/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pione
 */
public class TimeController {
    private long startTime;
    private int time = 12;
    
    public void nightStart(){
        startTime = System.currentTimeMillis() - 5000;
    }
    
    public void getTime(){
        if((System.currentTimeMillis() - startTime / 1000) % 45 == 0){
            System.out.println("HI");
        }
        return;
    }
}
