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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class CancelDialog extends AnchorPane implements Initializable{
    private PowerOffWindow father;
    public Label info;
    public Button detenerButton;
    public Button cancelButton;
    
    private CancelDialog(PowerOffWindow pow){
        father = pow;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/CancelDialog.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    public static void show(PowerOffWindow pow){
        AnchorPane root = new CancelDialog(pow);
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
    private void handleCancelar(){
        this.getScene().getWindow().hide();
    }
    
    @FXML
    private void handleDetener(){
        try{
            father.power.cancelShutdown();
        }catch(Exception ex){}
        handleCancelar();
    }
}
