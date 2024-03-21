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
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Afia and Nahin
 */
public class WebPageController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField searchBar;
    @FXML
    private WebView webView;

    private WebEngine engine;
    private WebHistory history;

    private String homePage;

    private double zoom; // for zoomin zoomout

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        engine = webView.getEngine();
        homePage = "www.google.com";
        searchBar.setText(homePage);
        zoom = 1;
        loadWebPage();

    }

    //loading a page
    public void loadWebPage() {
        engine.load("http://www.google.com");//
        engine.load("http://" + searchBar.getText());
    }

    //refreshing a page
    public void refreshWebPage() {
        engine.reload();
    }

    public void zoomIn() {
        zoom += 0.25;
        webView.setZoom(zoom);
    }

    public void zoomOut() {
        zoom -= 0.25;
        webView.setZoom(zoom);
    }

    public void forward() {

        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        history.go(1); // to visit next page
        searchBar.setText(entries.get(history.getCurrentIndex()).getUrl()); // to update searchbar with current website
    }

    public void backward() {

        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        history.go(-1); // to visit previus page
        searchBar.setText(entries.get(history.getCurrentIndex()).getUrl()); // to update searchbar with current website
    }

    public void takeToHistory(ActionEvent event) {
        try {
            history = engine.getHistory();
            ObservableList<WebHistory.Entry> entries = history.getEntries();

            for (WebHistory.Entry entry : entries) {

                System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
                System.out.println("***");
            }
            root = FXMLLoader.load(getClass().getResource("history.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getCause().printStackTrace();
            System.out.println("CANT LOAD HISTORY");
        }
    }

    public void showCreators(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("CreatorInfo.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.getCause().printStackTrace();
            System.out.println("CANT LOAD CI");
        }
    }

    // show history in console
    public void showHistory(ActionEvent event) {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        
         for (WebHistory.Entry entry : entries) {

                System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
                System.out.println("***");
            }
    }

}
