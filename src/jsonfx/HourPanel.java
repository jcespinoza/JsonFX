package jsonfx;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import jfxtras.labs.scene.control.CalendarTimePicker;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class HourPanel extends AnchorPane implements Initializable{
    private PowerOffWindow father;
    public Button defaultButton;
    public Label info;
    public AnchorPane place;
    private CalendarTimePicker time;
    
    public HourPanel(PowerOffWindow pof){
        father = pof;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/HourPanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        info.setText("Hora de " + father.action[4]);
        defaultButton.setText(father.action[0] +"!");
        placeTimePicker();
    }

    @FXML
    private void handleBack(){
        father.loadMainPanel();
    }
    
    @FXML
    private void handleDefault(){
        Calendar c = time.getCalendar();
        try{
            father.power.cancelShutdown();
            if( father.conf.shutdown() )
                father.power.shutdownAt(c);
            else
                father.power.restartAt(c);
        }catch(Exception ex){}
        System.out.println("Shutting down at: " + c.getTime());
    }

    private void placeTimePicker() {
        time = new CalendarTimePicker();
        time.setShowLabels(true);
        AnchorPane.setTopAnchor(time, 0.0);
        AnchorPane.setBottomAnchor(time, 0.0);
        AnchorPane.setLeftAnchor(time, 0.0);
        AnchorPane.setRightAnchor(time, 0.0);
        place.getChildren().add(time);
    }
}
