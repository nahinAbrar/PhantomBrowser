/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phantombrowser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Afia and Nahin
 */
public class HistoryController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;
    private WebEngine engine;
    private WebHistory history;
    
    
    
    
    @FXML
    TextArea showH;
    

    public void showHistory() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        for (WebHistory.Entry entry : entries) {

            showH.appendText(entry.getUrl() + " " + entry.getLastVisitedDate());
            showH.appendText("***");
        }
    }

    public void goBack(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("WebPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getCause().printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showHistory();
    }

}
