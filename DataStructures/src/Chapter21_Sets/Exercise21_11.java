/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter21_Sets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise21_11 extends Application {
    // Hold name data
    private HashMap<String, String>[] maleNames = new HashMap[10];
    private HashMap<String, String>[] femaleNames = new HashMap[10];
    private ComboBox<Integer> cboYears = new ComboBox<>();
    private ComboBox<String> cboGender = new ComboBox<>();
    private TextField tfName = new TextField();
    private Button btnFindRank = new Button("Find Ranking");
    private Label lblNameRank = new Label();
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, MalformedURLException, IOException {
        // Create GUI
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        
        // Year options
        pane.add(new Text("Select a year:"), 0, 0);
        for (int i = 2001; i <= 2010; i++) { // Populate year combo box
            cboYears.getItems().add(i);
        }
        cboYears.setValue(cboYears.getItems().get(0));
        pane.add(cboYears, 1, 0);
        
        // Gender options
        pane.add(new Label("Boy or girl?"), 0, 1);
        cboGender.getItems().addAll("Male", "Female"); // Populate gender combo box
        cboGender.setValue(cboGender.getItems().get(0));
        pane.add(cboGender, 1, 1);
        
        // User input for name
        pane.add(new Label("Enter a name:"), 0, 2);
        pane.add(tfName, 1, 2);
        
        // Find and display name rank
        pane.add(btnFindRank, 1, 3);
        pane.add(lblNameRank, 0, 4, 2, 1);
        
        btnFindRank.setOnAction(e -> {
            lblNameRank.setText(findNameRank());
        });

        // Set GUI to show in window
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setTitle("Exercise21_11");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        for (int i = 0; i < 10; i++) {
            maleNames[i] = new HashMap<>();
            femaleNames[i] = new HashMap<>();
        }
        
        // Fill in name data
        for (int i = 2001; i <= 2010; i++) { // Iterate through years
            Scanner input = new Scanner(new URL("http://www.cs.armstrong.edu/liang/data/babynamesranking" + Integer.toString(i) + ".txt").openStream());
            
            while (input.hasNext()) {
                String ranking, maleName, femaleName;
                ranking = input.next();
                maleName = input.next();
                input.next();
                femaleName = input.next();
                input.next();
                
                maleNames[i - 2001].put(maleName, ranking);
                femaleNames[i - 2001].put(femaleName, ranking);
            }
        }
    }
    
    /** Returns message of name rank */
    public String findNameRank() {
        String rankMsg = "ranked #";
        String gender = cboGender.getValue();
        int year = cboYears.getValue();
        String name = tfName.getText();
        
        if ("Male".equals(gender)){
            String rank = maleNames[year - cboYears.getItems().get(0)].get(name);
            rankMsg = (rank == null) ? "not ranked" : rankMsg + rank;
            gender = "Boy";
        }
        else {
            String rank = femaleNames[year - cboYears.getItems().get(0)].get(name);
            rankMsg = (rank == null) ? "not ranked" : rankMsg + rank;
            gender = "Girl";
        }
        
        return gender + " name " + name + " is " + rankMsg + " in year " + Integer.toString(year);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}