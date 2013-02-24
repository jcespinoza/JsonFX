package jsonfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import jfxtras.labs.scene.control.ListSpinner;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class TimerPanel extends AnchorPane implements Initializable{

    private PowerOffWindow father;
    public Button defaultButton;
    public AnchorPane hPlace;
    public AnchorPane mPlace;
    public AnchorPane sPlace;
    
    private ListSpinner<Integer> hours;
    private ListSpinner<Integer> mins;
    private ListSpinner<Integer> secs;
    
    public TimerPanel(PowerOffWindow pof){
        father = pof;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/TimerPanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        defaultButton.setText(father.action[0] + "!");
        placeSpinners();
    }
    
    @FXML
    private void handleBack(){
        father.loadMainPanel();
    }
    
    @FXML
    private void handleDefault(){
        long secs = getSeconds();
        try{
            father.power.cancelShutdown();
            if( father.conf.shutdown() )
                father.power.shutdownIn(secs);
            else
                father.power.restartIn(secs);
        }catch(Exception ex){}
        System.out.println("Shutting down in " + secs + " seconds");
    }

    private void placeSpinners() {
        hours = new ListSpinner<Integer>(0, 24)
                .withCyclic(Boolean.TRUE)
                .withEditable(Boolean.TRUE);
        configureSpinner(hours);
        
        mins = new ListSpinner<>(0, 60);
        configureSpinner(mins);
        
        secs = new ListSpinner<>(0, 60);
        configureSpinner(secs);
        
        hPlace.getChildren().add(hours);
        mPlace.getChildren().add(mins);
        sPlace.getChildren().add(secs);
    }
    
    private void setAnchors(Node n){
        AnchorPane.setTopAnchor(n, 0.0);
        AnchorPane.setBottomAnchor(n, 0.0);
        AnchorPane.setLeftAnchor(n, 0.0);
        AnchorPane.setRightAnchor(n, 0.0);
    }
    
    private void configureSpinner(ListSpinner<Integer> n){
        n.setArrowDirection(ListSpinner.ArrowDirection.VERTICAL);
        n.setArrowPosition(ListSpinner.ArrowPosition.SPLIT);
        n.setAlignment(Pos.CENTER);
        setAnchors(n);
        n.setStyle("-fx-font: 60px 'Cambria'");
    }

    private long getSeconds() {
        long res = 0;
        long h = hours.getValue() * 3600;
        long m = mins.getValue() * 60;
        long s = secs.getValue();
        res = h + m + s;
        return res;
    }
}
