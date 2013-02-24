package jsonfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class ConfigDialog extends AnchorPane implements Initializable{
    private PowerOffWindow father;
    @FXML
    private RadioButton apagarRadio;
    public RadioButton restartRadio;
    
    private ConfigDialog(PowerOffWindow pow){
        father = pow;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/ConfigDialog.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apagarRadio.setSelected( father.conf.shutdown() );
        restartRadio.setSelected( father.conf.restart() );
    }
    
    public static void show(PowerOffWindow pow){
        AnchorPane root = new ConfigDialog(pow);
        Scene scene = new Scene(root);
        Stage st = new Stage();
        st.setScene(scene);
        st.sizeToScene();
        st.initOwner(pow.getScene().getWindow());
        st.setResizable(false);
        st.initModality(Modality.WINDOW_MODAL);
        st.show();
    }
    
    @FXML
    private void handleApply(){
        father.conf.setShutdown( apagarRadio.isSelected() );
        father.loadStrings();
        father.reloadPanel();
        this.getScene().getWindow().hide();
    }
}
