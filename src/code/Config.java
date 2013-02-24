/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class Config implements Serializable{
    private boolean shutdown;
    private boolean restart;
    
    public Config(){
        setShutdown(true);
    }

    public void setShutdown(boolean val) {
        shutdown = val;
        restart = !val;
    }
    
    public boolean shutdown(){
        return shutdown;
    }
    
    public boolean restart(){
        return restart;
    }
    
    public void setRestart(boolean val){
        setShutdown(!val);
    }
    
    public static Config readFromFile(String path){
        File f = new File(path);
        if( !f.exists() )
            return new Config();
        else{
            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                return (Config)( ois.readObject() );
            } catch (Exception ex) {
                return new Config();
            }
        }
    }
    
    public static void writeToFile(String path, Config conf){
        File f = new File(path);
        try {
            if( f.exists() )
                f.delete();
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream ous = new ObjectOutputStream(fout);
            ous.writeObject(conf);
            String p = f.getAbsolutePath();
            Runtime.getRuntime().exec("attrib +h " + p);
        } catch (Exception ex) {}
    }
}