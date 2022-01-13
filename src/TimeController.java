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
    private long hourTime;
    private int time;
    
    public void nightStart(){
        hourTime = System.currentTimeMillis() - 5000;
        time = 12;
    }
    
    public int getTime(){
        //determines if 45 seconds have passed and changes to the next hour
        if(((System.currentTimeMillis() - hourTime) / 1000) % 46 == 0){
            if(this.time == 12){
                this.time = 1;
            }else{
                this.time++;
            }
            hourTime = System.currentTimeMillis() - 1000;
        }
        return this.time;
    }
}
