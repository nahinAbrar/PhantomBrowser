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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Afia and Nahin
 */
public class UserSignUpController {

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private Button back;
    @FXML
    private TextField userName, eMail;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField rePass;
    
    
   


    Connection con;

    public void goBack(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getCause().printStackTrace();
        }
    }

    public void registerSignUp(ActionEvent event) {

        
        try {

            con = DriverManager.getConnection("jdbc:derby://localhost:1527/WebSiteUserInfo", "nin", "1234");
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("SQL EXCEPTION");
            a.show();
            ex.getCause().printStackTrace();
        }

        Alert showAlert = new Alert(AlertType.NONE);

        String userN = userName.getText();
        String email = eMail.getText();
        String password = pass.getText();
        String confirmPassword = rePass.getText();
        


        if (userN.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
            showAlert.setAlertType(Alert.AlertType.ERROR);
            showAlert.setContentText("One or More fields are empty");
            showAlert.show();

        } else if (!password.equals(confirmPassword)) {
            showAlert.setAlertType(Alert.AlertType.ERROR);
            showAlert.setContentText("Wrong Password");
            showAlert.show();

        } else if (checkUsername(userN)) {
            showAlert.setAlertType(Alert.AlertType.ERROR);
            showAlert.setContentText("UserName Taken");
            showAlert.show();

        } else {

            String sql = "INSERT INTO DATA(USERNAME,PASSWORD,EMAIL) VALUES ('" + userN + "','" + password + "','" + email + "')";
            try {

                int row = -1;
                PreparedStatement state = con.prepareStatement(sql);
                row = state.executeUpdate();
                showAlert.setAlertType(Alert.AlertType.INFORMATION);
                showAlert.setContentText("Profile Created!");
                showAlert.show();

            } catch (SQLException ex) {
                showAlert.setAlertType(Alert.AlertType.ERROR);
                showAlert.setContentText("SQL EXCEPTION");
                showAlert.show();
                ex.getCause().printStackTrace();

            }
        }

//        try {
//            root = FXMLLoader.load(getClass().getResource("WebPage.fxml"));
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            System.out.println("FILE NOT FOUND ** WELCOME PAGE");
//        }
    }

    public boolean checkUsername(String userName) {
        PreparedStatement st;
        Alert showAlert = new Alert(AlertType.NONE);
        ResultSet res;
        boolean exists = false;
        String query = "SELECT * FROM DATA WHERE USERNAME=?";
        try {

            st = con.prepareStatement(query);
            st.setString(1, userName);
            res = st.executeQuery();
            if (res.next()) {
                exists = true;

            }
        } catch (SQLException ex) {
            showAlert.setAlertType(Alert.AlertType.ERROR);
            showAlert.setContentText("SQL EXCEPTION");
            showAlert.show();
        }
        return exists;

    }

}
