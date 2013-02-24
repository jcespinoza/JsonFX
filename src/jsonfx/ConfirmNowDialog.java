/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsonfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class ConfirmNowDialog extends AnchorPane implements Initializable{
    private PowerOffWindow father;
    public Label confirmLabel;
    
    private ConfirmNowDialog(PowerOffWindow pof){
        father = pof;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/ConfirmNowDialog.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        confirmLabel.setText("Seguro que quiere " + father.action[2] + " su computadora ahora?");
    }
    
    public static void show(PowerOffWindow pow){
        AnchorPane root = new ConfirmNowDialog(pow);
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
    private void handleYes(){
        try{
            if( father.conf.shutdown() )
                father.power.shutdownNow();
            else
                father.power.restarNow();
        }catch(Exception ex){}
        System.out.println(father.action[1] +"ndo inmediatamente");
    }

    @FXML
    private void handleNo(){
        this.getScene().getWindow().hide();
    }
}
