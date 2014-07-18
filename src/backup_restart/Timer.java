/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backup_restart;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;

 
/**
 *
 * @author Russell
 */
public class Timer extends JFrame{
  private final ScheduledExecutorService scheduler =
     Executors.newScheduledThreadPool(1);
  private Backup_Restart copy = new Backup_Restart();
  
    public Timer(){
        
    }
    
    public void copyFile() {
     final Runnable beeper = new Runnable() {
       public void run() {
           copy.main();
       }
     };
     //10800
     
     final ScheduledFuture<?> beeperHandle =
       scheduler.scheduleAtFixedRate(beeper, time, time, SECONDS);
     
    
     
}   
    
}
