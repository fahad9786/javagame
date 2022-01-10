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
    private int time = 12;
    
    public void nightStart(){
        hourTime = System.currentTimeMillis() + 5000;
    }
    
    public int getTime(){
        System.out.println(((System.currentTimeMillis() - hourTime) / 1000) % 46);
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
