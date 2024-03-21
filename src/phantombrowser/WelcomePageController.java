/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phantombrowser;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Afia and Nahin
 *
 */
public class WelcomePageController {

    private Stage stage;
    private Parent root;
    private Scene scene;



    


    //guest button event
    public void jumpToBrowser(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("WebPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.getCause().printStackTrace();
            System.out.println("FILE NOT FOUND ** MAIN WEB PAGE");
        }

    }

    //signup button event
    public void taketoSignUp(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("UserSignUp.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("FILE NOT FOUND ** SIGN UP PAGE");
        }
    }

    //signin button event
    public void taketoSignIn(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("UserSignIn.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getCause().printStackTrace();
            System.out.println("FILE NOT FOUND ** SIGN IN PAGE");
        }
    }

}
