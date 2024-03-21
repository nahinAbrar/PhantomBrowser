/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phantombrowser;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;


/**
 *
 * @author Afia and Nahin
 */


public class PhantomBrowser extends Application {

    @Override
    public void start(Stage stg) {
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
            Scene scene = new Scene(root);
            stg.setScene(scene);
            Image icn = new Image("file:Images/icon.jpg");
            stg.getIcons().add(icn);
            stg.setTitle("Phantom Browser");
            
            stg.show();
        } catch (IOException ex) {
            ex.getCause().printStackTrace();
            System.out.println("FXML file not found!.");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
