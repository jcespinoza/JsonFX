/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code;

import java.io.IOException;
import java.util.Calendar;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class PowerOff {
    private Runtime rt;
    
    public PowerOff(){
        rt = Runtime.getRuntime();
    }
    
    private void execute(String cmd) throws IOException{
        rt.exec(cmd);
    }
    
    public static void main(String[] args) {
        try {
            Calendar test = Calendar.getInstance();
            test.add(Calendar.HOUR_OF_DAY, 2);
            System.out.println("Actual: " + Calendar.getInstance().getTimeInMillis());
            System.out.println("Future: " + test.getTimeInMillis());
            PowerOff pof = new PowerOff();
            long s = pof.calculateSeconds(test);
            System.out.println("Seconds: " + s);
            pof.cancelShutdown();
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
    
    public void cancelShutdown() throws IOException{
        execute("shutdown /a");
    }
    
    public void shutdownIn(long seconds) throws IOException{
        execute("shutdown -s -t " + seconds);
    }
    
    public void shutdownNow() throws IOException{
        execute("shutdown -s -t 0");
    }
    
    public void shutdownAt(Calendar time) throws IOException{
        long secs = calculateSeconds(time);
        execute("shutdown -s -t " + secs);
    }
    
    public void restarNow() throws IOException{
        execute("shutdown -r -t 0");
    }
    
    public void restartIn(long seconds) throws IOException{
        execute("shutdown -r -t " + seconds);
    }
    
    public void restartAt(Calendar time) throws IOException{
        long secs = calculateSeconds(time);
        execute("shutdown -r -t " + secs);
    }
    
    public long calculateSeconds(Calendar time){
        long secs = 0;
        long millisT = time.getTimeInMillis();
        long millisA = Calendar.getInstance().getTimeInMillis();
        if( millisA < millisT)
            secs = (millisT / 1000) - ( millisA / 1000);
        if(secs > 0)
            return secs;
        return 0;
    }
}