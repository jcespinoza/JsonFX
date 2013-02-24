package jsonfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class MainPanel extends AnchorPane implements Initializable{
    private PowerOffWindow owner;
    public Button nowButton;
    public Tooltip nowTooltip;
    public Tooltip timerTooltip;
    public Tooltip hourTooltip;
    
    public MainPanel(PowerOffWindow pof){
        owner = pof;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/MainPanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //text change  
    nowButton.setText(owner.action[0] + " Ahora!");
    nowTooltip.setText(owner.action[1] + " la computadora de una maldita vez sin muchas preguntas.");
    timerTooltip.setText("Activa un " + owner.action[4] + " hasta que pase el tiempo especificado.");
    hourTooltip.setText("Activa un " + owner.action[4] + " a la hora especificada.");
    
        
    }
    
    @FXML
    private void handleTimer(MouseEvent e){
        owner.loadTimerPanel();
    }
    
    @FXML
    private void handleHour(MouseEvent e){
        owner.loadHourPanel();
    }

    @FXML
    private void handleNowButton(ActionEvent e){
        ConfirmNowDialog.show(owner);
    }
}