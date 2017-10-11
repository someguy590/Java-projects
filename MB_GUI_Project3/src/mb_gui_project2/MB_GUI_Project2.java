/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb_gui_project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class MB_GUI_Project2 extends Application {
    final static String[] ATTRIBUTE_TITLES = {"STR", "AGI", "INT", "CHA"};
    final static String[] SKILL_TITLES = {
        "Ironflesh", "Power Strike", "Power Throw", "Power Draw",
        "Weapon Master", "Shield", "Athletics", "Riding", "Horse Archery",
        "Looting", "Trainer", "Tracking", "Tactics", "Path-finding",
        "Spotting", "Inventory Management", "Wound Treatment", "Surgery",
        "First Aid", "Engineer", "Persuasion", "Prisoner Management",
        "Leadership", "Trade"};
    final static String[] PROFICIENCY_TITLES = {
        "One-handed Weapons", "Two-handed Weapons", "Polearms", "Archery", 
        "Crossbows", "Thrown Weapons"};
    
    final static HashMap<String, Integer> attributeValues = new HashMap<>();
    final static HashMap<String, Integer> skillValues = new HashMap<>();
    final static HashMap<String, Integer> proficiencyValues = new HashMap<>();
    
    final static HashMap<ToggleGroup, List<String>> selectedChoices = new HashMap<>();
    
    final static VBox middleViewPane = new VBox(50);
    final static VBox rightViewPane = new VBox(50);
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // Initialize attribute, skill, and proficiency values
        for (String s : ATTRIBUTE_TITLES)
            attributeValues.put(s, 0);
        for (String s : SKILL_TITLES)
            skillValues.put(s, 0);
        for (String s : PROFICIENCY_TITLES)
            proficiencyValues.put(s, 0);
        
        File file = new File("src/yeah/Mount&Blade character creation wiki page.txt");
        Scanner input = new Scanner(file);
        
            
        VBox leftPane = new VBox();
        String originSectionTitle = "";
        String originSectionChoiceHeaders = "";
        ArrayList<String> originSectionChoices = new ArrayList<>();
        
        while (input.hasNext()) {
            String line = input.nextLine();
            
            // Find origin section
            if (line.contains("Base")) {
                parseBase(input, line);
            }
            
            // Find origin choice headers
            if (line.contains("Edit") && !line.contains("Base")) {
                leftPane.getChildren().add(parseSection(input, line));
            }
        }
        
        // Left pane
        ToggleGroup gender = new ToggleGroup();
        //gender.
        RadioButton radioBtnMale = new RadioButton("Male");
        RadioButton radioBtnFemale = new RadioButton("Female");
        radioBtnMale.setToggleGroup(gender);
        radioBtnFemale.setToggleGroup(gender);
        radioBtnMale.setSelected(true);
        VBox genderPane = new VBox(radioBtnMale, radioBtnFemale);
        
        leftPane.getChildren().add(0, genderPane);
        leftPane.setSpacing(30);
        leftPane.setAlignment(Pos.CENTER);
        ScrollPane leftViewPane = new ScrollPane(leftPane);
        
        // Middle pane
        middleViewPane.setAlignment(Pos.CENTER);
        middleViewPane.getChildren().addAll(
                getStatPane(attributeValues), 
                getStatPane(proficiencyValues));
        
        // Right pane
        rightViewPane.getChildren().add(getStatPane(skillValues));
        
        // Root pane
        HBox root = new HBox(
                leftViewPane,
                middleViewPane,
                rightViewPane);
        root.setAlignment(Pos.CENTER);
        
        updateStats();
        
        // Set scene
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.hide();
    }
    
    public static void parseBase(Scanner input, String currentLine) {
        System.out.println(currentLine);
    }
    
    public static VBox parseSection(Scanner input, String currentLine) {
        String sectionTitle = currentLine.replace("Edit", "").replace("\t", "");
        String[] sectionHeaders = input.nextLine().split("\t");
        
        ArrayList<String> choices = new ArrayList<>();
        String line = input.nextLine();
        while (!line.isEmpty() && input.hasNext()) {
            choices.add(line);
            line = input.nextLine();
        }
        
        return getOriginSectionPane(sectionTitle, sectionHeaders, choices);
    }
    
    private static VBox getOriginSectionPane(String sectionName,
            String[] choiceHeaders, List<String> choices) {
        VBox root = new VBox();
        
        // Add section title
        root.getChildren().add(new Text(sectionName));
        
        ToggleGroup group = new ToggleGroup();
        for (String choice : choices) {
            ArrayList<String> choiceRewards = new ArrayList<>(Arrays.asList(choice.split("\t")));
            while (choiceRewards.size() < choiceHeaders.length)
                choiceRewards.add("");
            
            int i = 0;
            for (String h : choiceHeaders) {
                if (i == 0) {
                    RadioButton btn = new RadioButton(choiceRewards.get(i));
                    root.getChildren().add(btn);
                    
                    btn.setToggleGroup(group);
                    btn.setOnAction((ActionEvent e) -> {
                        selectedChoices.put(group, choiceRewards);
                        updateStats();
                    });
                }
                else {
                    root.getChildren().add(new Text("\t" + h + ": " + choiceRewards.get(i)));
                }
                i++;
            }
        }
        
        group.getToggles().get(0).setSelected(true);
        List<String> defaultRewards = Arrays.asList(choices.get(0).split("\t"));
        selectedChoices.put(group, defaultRewards);
        
        return root;
    }
    
    public static void updateStats() {
        middleViewPane.getChildren().clear();
        rightViewPane.getChildren().clear();
        
        for (String key : attributeValues.keySet())
            attributeValues.replace(key, 0);
        
        for (String key : skillValues.keySet())
            skillValues.replace(key, 0);
        
        for (String key : proficiencyValues.keySet())
            proficiencyValues.replace(key, 0);
        
        for (List<String> choiceRewards : selectedChoices.values()) {
            for (String reward : choiceRewards) {
                for (String subReward : reward.split(",")) {
                    subReward = subReward.trim();
                    int rewardEndIndex = subReward.indexOf("+") - 1;
                    if (rewardEndIndex < 0)
                        continue;
                    String r = subReward.substring(0, rewardEndIndex);
                    int rewardAmount = Integer.valueOf(subReward.charAt(subReward.indexOf("+") + 1) + "");

                    // Update attributes
                    if (doesListMatchWord(ATTRIBUTE_TITLES, subReward)) {
                        if (attributeValues.containsKey(r))
                            attributeValues.replace(
                                    r, attributeValues.get(r) + rewardAmount);
                    }

                    // Update skills
                    if (doesListMatchWord(SKILL_TITLES, subReward)) {
                        if (skillValues.containsKey(r))
                            skillValues.replace(
                                    r, skillValues.get(r) + rewardAmount);
                    }

                    // Update proficiencies
                    if (doesListMatchWord(PROFICIENCY_TITLES, subReward)) {
                        if (proficiencyValues.containsKey(r))
                            ;//proficiencyValues.replace(r, proficiencyValues.get(r) + rewardAmount);
                    }
                }
            }
        }
        
        middleViewPane.getChildren().addAll(
                getStatPane(attributeValues),
                getStatPane(proficiencyValues));
        rightViewPane.getChildren().add(getStatPane(skillValues));
    }
    
    public static VBox getStatPane(HashMap<String, Integer> statValues) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        
        for (Map.Entry<String, Integer> e : statValues.entrySet())
            root.getChildren().add(new Text(e.getKey() + ": " + e.getValue()));
        
        return root;
    }
    
    public static boolean doesListMatchWord(String[] list, String word) {
        for (String s : list) {
            if (word.contains(s))
                return true;
        }
        
        return false;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
