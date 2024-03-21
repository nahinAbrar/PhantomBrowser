/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phantombrowser;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Nahin
 */
public class CreatorInfoController{
    
    
    private Stage stage;
    private Parent root;
    private Scene scene;
    
     public void goBack(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("WebPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("FILE NOT FOUND ** WebPage not found");
        }
    }


}
