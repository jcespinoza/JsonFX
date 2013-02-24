package jsonfx;

import code.Config;
import code.PowerOff;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * @author Jay C Espinoza
 */
public final class PowerOffWindow extends AnchorPane implements Initializable{
    public PowerOff power;
    private String[] ap = {"Apagar", "Apaga", "apagar", "apaga", "apagado"};
    private String[] re = {"Reiniciar", "Reinicia", "reiniciar", "renicia", "reinicio"};
    public String[] action;
    public Config conf;
    public String confPath = "config.json";
    public AnchorPane content;
    private int currentPanel = 1;
    
    public PowerOffWindow(Object owner){
        try {
            FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/PowerOffRoot.fxml"));
            fx.setRoot(this);
            fx.setController(this);
            fx.load();
        } catch (Exception ex){
            System.out.println("Exception");
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        power = new PowerOff();
        conf = Config.readFromFile(confPath);
        loadStrings();
        loadMainPanel();
    }
    
    public void loadMainPanel() {
        MainPanel m = new MainPanel(this);
        changeContent(m);
        currentPanel = 1;
    }
    
    public void loadTimerPanel(){
        TimerPanel t = new TimerPanel(this);
        changeContent(t);
        currentPanel = 2;
    }
    
    public void loadHourPanel(){
        HourPanel h = new HourPanel(this);
        changeContent(h);
        currentPanel = 3;
    }
    
    @FXML
    private void showCancelDialog(){
        CancelDialog.show(this);
    }
    
    @FXML
    private void showConfigDialog(){
        ConfigDialog.show(this);
    }
    
    private void changeContent(Node n){
        emptyContent();
        AnchorPane.setBottomAnchor(n, 0.0);
        AnchorPane.setTopAnchor(n, 0.0);
        AnchorPane.setLeftAnchor(n, 0.0);
        AnchorPane.setRightAnchor(n, 0.0);
        content.getChildren().add(n);
    }
    
    public void reloadPanel(){
        switch(currentPanel){
        case 1:
            loadMainPanel();
            return;
        case 2:
            loadTimerPanel();
            return;
        case 3:
            loadHourPanel();
            return;
        }
    }
    
    public void loadStrings(){
        if( conf.restart() )
            action = re;
        else
            action = ap;
    }

    private void emptyContent() {
        content.getChildren().removeAll(content.getChildren());
    }
    
    
    @FXML
    private void handleSalir(ActionEvent e){
        Config.writeToFile(confPath, conf);
        Platform.exit();
    }
}
