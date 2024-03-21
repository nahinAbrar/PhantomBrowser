/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phantombrowser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Afia and Nahin
 */
public class UserSignInController {

    private Stage stage;
    private Parent root;
    private Scene scene;

   
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;

    Connection con;

    public void goBack(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("FILE NOT FOUND ** WELCOME PAGE");
        }
    }

    public void takeSignIn(ActionEvent event) {

        try {

            con = DriverManager.getConnection("jdbc:derby://localhost:1527/WebSiteUserInfo", "nin", "1234");
        } catch (SQLException ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("SQL EXCEPTION");
        }

        String username = userName.getText();
        String password = passWord.getText();

        try {
            String url = "SELECT USERNAME,PASSWORD FROM DATA WHERE USERNAME=? AND PASSWORD =?";

            PreparedStatement state = con.prepareStatement(url);

            state.setString(1, username);
            state.setString(2, password);
            ResultSet res = state.executeQuery();

            if (res.next()) {
                try {
                    root = FXMLLoader.load(getClass().getResource("WebPage.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println("FILE NOT FOUND ** MAIN WEB PAGE");
                }

            } else {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Username or Password Wrong.");
                a.show();
            }
        } catch (SQLException ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("SQL EXCEPTION");
            a.show();

        }
    }

}
