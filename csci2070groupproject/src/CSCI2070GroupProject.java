/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author davidlewis
 */
public class CSCI2070GroupProject extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Add three buttons for Encrypt, Clear, and Decrypt
        Button encryptBtn = new Button("Encrypt");
        Button clear = new Button("Clear");
        Button decryptBtn = new Button("Decrypt");
        //Combobox
        ComboBox algorithm = new ComboBox();
        algorithm.getItems().addAll(
                "ECB",
                "CBC",
                "CTR"
        );
        algorithm.getSelectionModel().select(0);
        algorithm.setStyle("-fx-font: 14 calibri; -fx-base: #F6CF3F;");
        //TextFields for the 4 textfields
        TextField text = new TextField();
        text.setStyle("-fx-base: #F6CF3F;");
        text.setMaxWidth(200);
        TextField ciphertext = new TextField("");
        ciphertext.setStyle("-fx-base: #F6CF3F;");
        ciphertext.setMaxWidth(200);
        TextField dec = new TextField();
        dec.setStyle("-fx-base: #F6CF3F;");
        dec.setMaxWidth(200);
        TextField plain = new TextField("");
        plain.setStyle("-fx-base: #F6CF3F;");
        plain.setMaxWidth(200);

        //E-Key 1 controls
        ComboBox<Integer>[] keyParts1 = new ComboBox[9];
        for (int i = 1; i <= keyParts1.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 5) {
                keyPart.getItems().addAll(i, 10 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 5) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(10 - i, i);
                keyPart.getSelectionModel().select(1);
            }

            keyParts1[i - 1] = keyPart;
        }
        HBox hBoxKey1 = new HBox(keyParts1);

        TextField key1 = new TextField();
        key1.setStyle("-fx-base: #F6CF3F;");
        key1.setMaxWidth(100);
        Label key1lbl = new Label("Encryption Key 1:");
        key1lbl.setFont(new Font("Calibri", 16));
        key1lbl.setTextFill(Color.web("#F6CF3F"));

        //E-Key 2 Controls
        ComboBox<Integer>[] keyParts2 = new ComboBox[9];
        for (int i = 1; i <= keyParts2.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 5) {
                keyPart.getItems().addAll(i, 10 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 5) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(10 - i, i);
                keyPart.getSelectionModel().select(1);
            }


            keyParts2[i - 1] = keyPart;
        }
        HBox hBoxKey2 = new HBox(keyParts2);

        TextField key2 = new TextField();
        key2.setStyle("-fx-base: #F6CF3F;");
        key2.setMaxWidth(100);
        Label key2lbl = new Label("Encryption Key 2:");
        key2lbl.setFont(new Font("Calibri", 16));
        key2lbl.setTextFill(Color.web("#F6CF3F"));


        //E-Key 3 Controls
        ComboBox<Integer>[] keyParts3 = new ComboBox[9];
        for (int i = 1; i <= keyParts3.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 5) {
                keyPart.getItems().addAll(i, 10 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 5) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(10 - i, i);
                keyPart.getSelectionModel().select(1);
            }


            keyParts3[i - 1] = keyPart;
        }
        HBox hBoxKey3 = new HBox(keyParts3);

        TextField key3 = new TextField();
        key3.setStyle("-fx-base: #F6CF3F;");
        key3.setMaxWidth(100);
        Label key3lbl = new Label("Encryption Key 3:");
        key3lbl.setFont(new Font("Calibri", 16));
        key3lbl.setTextFill(Color.web("#F6CF3F"));


        //E-Key 4 Controls
        ComboBox<Integer>[] keyParts4 = new ComboBox[9];
        for (int i = 1; i <= keyParts4.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 5) {
                keyPart.getItems().addAll(i, 10 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 5) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(10 - i, i);
                keyPart.getSelectionModel().select(1);
            }

            keyParts4[i - 1] = keyPart;
        }
        HBox hBoxKey4 = new HBox(keyParts4);

        TextField key4 = new TextField();
        key4.setStyle("-fx-base: #F6CF3F;");
        key4.setMaxWidth(100);
        Label key4lbl = new Label("Encryption Key 4:");
        key4lbl.setFont(new Font("Calibri", 16));
        key4lbl.setTextFill(Color.web("#F6CF3F"));


        //E-Key 5 Controls
        ComboBox<Integer>[] keyParts5 = new ComboBox[19];
        for (int i = 1; i <= keyParts5.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 10) {
                keyPart.getItems().addAll(i, 20 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 10) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(20 - i, i);
                keyPart.getSelectionModel().select(1);
            }

            keyParts5[i - 1] = keyPart;
        }
        HBox hBoxKey5 = new HBox(keyParts5);

        TextField key5 = new TextField();
        key5.setStyle("-fx-base: #F6CF3F;");
        key5.setMaxWidth(100);
        Label key5lbl = new Label("Encryption Key 5:");
        key5lbl.setFont(new Font("Calibri", 16));
        key5lbl.setTextFill(Color.web("#F6CF3F"));

        // display error message in case of bad keys
        Font font = new Font("Calibri", 16);
        Color color = Color.web("#F6CF3F");
        String errorMessage = "Key is incorrect";
        Label[] lblEncryptErrorMessages = new Label[5];
        for (int i = 0; i < lblEncryptErrorMessages.length; i++) {
            lblEncryptErrorMessages[i] = new Label();
            lblEncryptErrorMessages[i].setFont(font);
            lblEncryptErrorMessages[i].setTextFill(color);
        }

        //D-Key1 Controls
        ComboBox<Integer>[] keyParts1Decrypt = new ComboBox[9];
        for (int i = 1; i <= keyParts1Decrypt.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 5) {
                keyPart.getItems().addAll(i, 10 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 5) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(10 - i, i);
                keyPart.getSelectionModel().select(1);
            }

            keyParts1Decrypt[i - 1] = keyPart;
        }
        HBox hBoxKey1Decrypt = new HBox(keyParts1Decrypt);

        TextField dkey1 = new TextField();
        dkey1.setStyle("-fx-base: #F6CF3F;");
        dkey1.setMaxWidth(100);
        Label dkey1lbl = new Label("Decryption Key 1:");
        dkey1lbl.setFont(new Font("Calibri", 16));
        dkey1lbl.setTextFill(Color.web("#F6CF3F"));

        //D-Key2 Controls
        ComboBox<Integer>[] keyParts2Decrypt = new ComboBox[9];
        for (int i = 1; i <= keyParts2Decrypt.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 5) {
                keyPart.getItems().addAll(i, 10 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 5) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(10 - i, i);
                keyPart.getSelectionModel().select(1);
            }

            keyParts2Decrypt[i - 1] = keyPart;
        }
        HBox hBoxKey2Decrypt = new HBox(keyParts2Decrypt);

        TextField dkey2 = new TextField();
        dkey2.setStyle("-fx-base: #F6CF3F;");
        dkey2.setMaxWidth(100);
        Label dkey2lbl = new Label("Decryption Key 2:");
        dkey2lbl.setFont(new Font("Calibri", 16));
        dkey2lbl.setTextFill(Color.web("#F6CF3F"));

        //D-Key3 Controls
        ComboBox<Integer>[] keyParts3Decrypt = new ComboBox[9];
        for (int i = 1; i <= keyParts3Decrypt.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 5) {
                keyPart.getItems().addAll(i, 10 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 5) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(10 - i, i);
                keyPart.getSelectionModel().select(1);
            }

            keyParts3Decrypt[i - 1] = keyPart;
        }
        HBox hBoxKey3Decrypt = new HBox(keyParts3Decrypt);

        TextField dkey3 = new TextField();
        dkey3.setStyle("-fx-base: #F6CF3F;");
        dkey3.setMaxWidth(100);
        Label dkey3lbl = new Label("Decryption Key 3:");
        dkey3lbl.setFont(new Font("Calibri", 16));
        dkey3lbl.setTextFill(Color.web("#F6CF3F"));

        //D-Key4 Controls
        ComboBox<Integer>[] keyParts4Decrypt = new ComboBox[9];
        for (int i = 1; i <= keyParts4Decrypt.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 5) {
                keyPart.getItems().addAll(i, 10 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 5) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(10 - i, i);
                keyPart.getSelectionModel().select(1);
            }

            keyParts4Decrypt[i - 1] = keyPart;
        }
        HBox hBoxKey4Decrypt = new HBox(keyParts4Decrypt);

        TextField dkey4 = new TextField();
        dkey4.setStyle("-fx-base: #F6CF3F;");
        dkey4.setMaxWidth(100);
        Label dkey4lbl = new Label("Decryption Key 4:");
        dkey4lbl.setFont(new Font("Calibri", 16));
        dkey4lbl.setTextFill(Color.web("#F6CF3F"));

        //DKey5 Controls
        ComboBox<Integer>[] keyParts5Decrypt = new ComboBox[19];
        for (int i = 1; i <= keyParts5Decrypt.length; i++) {
            ComboBox<Integer> keyPart = new ComboBox<>();
            if (i < 10) {
                keyPart.getItems().addAll(i, 20 - i);
                keyPart.getSelectionModel().select(0);
            }
            else if (i == 10) {
                keyPart.getItems().add(i);
                keyPart.getSelectionModel().select(0);
            }
            else {
                keyPart.getItems().addAll(20 - i, i);
                keyPart.getSelectionModel().select(1);
            }

            keyParts5Decrypt[i - 1] = keyPart;
        }
        HBox hBoxKey5Decrypt = new HBox(keyParts5Decrypt);

        TextField dkey5 = new TextField();
        dkey5.setStyle("-fx-base: #F6CF3F;");
        dkey5.setMaxWidth(100);
        Label dkey5lbl = new Label("Decryption Key 5:");
        dkey5lbl.setFont(new Font("Calibri", 16));
        dkey5lbl.setTextFill(Color.web("#F6CF3F"));

        // display error message in case of bad keys
        Label[] lblDecryptErrorMessages = new Label[5];
        for (int i = 0; i < lblDecryptErrorMessages.length; i++) {
            lblDecryptErrorMessages[i] = new Label();
            lblDecryptErrorMessages[i].setFont(font);
            lblDecryptErrorMessages[i].setTextFill(color);
        }

        //Plaintext input message
        Label lbl = new Label("Enter Plaintext file path:");
        lbl.setFont(new Font("Calibri", 16));
        lbl.setTextFill(Color.web("#F6CF3F"));
        //Ciphertext result message
        Label resultlbl = new Label("Ciphertext:");
        resultlbl.setFont(new Font("Calibri", 16));
        resultlbl.setTextFill(Color.web("#F6CF3F"));
        //Ciphertext input message
        Label decodelbl = new Label("Enter Ciphertext file path:");
        decodelbl.setFont(new Font("Calibri", 16));
        decodelbl.setTextFill(Color.web("#F6CF3F"));
        //Plaintext result message
        Label space = new Label ("");
        Label ptext = new Label("Plaintext:");
        ptext.setFont(new Font("Calibri", 16));
        ptext.setTextFill(Color.web("#F6CF3F"));
        //Images
        Image img = new Image("/csci2070groupproject/armstrong.png");
        ImageView imgView = new ImageView(img);
        //Effects for buttons
        DropShadow shadow = new DropShadow();
        encryptBtn.setStyle("-fx-font: 16 calibri; -fx-base: #F6CF3F;");
        clear.setStyle("-fx-font: 16 calibri; -fx-base: #F6CF3F;");
        decryptBtn.setStyle("-fx-font: 16 calibri; -fx-base: #F6CF3F;");

        //Signature
        Label signature = new Label("David L., Johnathan M., Jayde S. ");
        signature.setFont(new Font("Calibri", 16));
        signature.setTextFill(Color.web("#F6CF3F"));

        encryptBtn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        encryptBtn.setEffect(shadow);
                    }
                });
        encryptBtn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        encryptBtn.setEffect(null);
                    }
                });

        encryptBtn.setOnAction(e -> {
            int[][] keys = new int[4][9];
            int[] blockKey = new int[19];

            // reset key error messages
            for (int i = 0; i < lblEncryptErrorMessages.length; i++)
                lblEncryptErrorMessages[i].setText("");

            boolean isKeysValid = true;
            ArrayList<Integer> checkedValues = new ArrayList<>();
            // check key 1
            for (int i = 0; i < keyParts1.length; i++) {
                int value = keyParts1[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblEncryptErrorMessages[0].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                keys[0][i] = checkedValues.get(i);
            checkedValues.clear();

            // check key 2
            for (int i = 0; i < keyParts2.length; i++) {
                int value = keyParts2[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblEncryptErrorMessages[1].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                keys[1][i] = checkedValues.get(i);
            checkedValues.clear();

            // check key 3
            for (int i = 0; i < keyParts3.length; i++) {
                int value = keyParts3[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblEncryptErrorMessages[2].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                keys[2][i] = checkedValues.get(i);
            checkedValues.clear();

            // check key 4
            for (int i = 0; i < keyParts4.length; i++) {
                int value = keyParts4[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblEncryptErrorMessages[3].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                keys[3][i] = checkedValues.get(i);
            checkedValues.clear();

            // check block key
            for (int i = 0; i < keyParts5.length; i++) {
                int value = keyParts5[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblEncryptErrorMessages[4].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                blockKey[i] = checkedValues.get(i);
            checkedValues.clear();

            // if a key is not valid do not run program
            if (!isKeysValid)
                return;

            File file = new File(text.getText());
            DiagonalEncrypt diagonalEncrypt = new DiagonalEncrypt(keys, blockKey);

            if (algorithm.getSelectionModel().getSelectedItem().equals("ECB"))
                diagonalEncrypt.setEncryptionMode(DiagonalEncrypt.EncryptionMode.ECB);
            else if (algorithm.getSelectionModel().getSelectedItem().equals("CBC"))
                diagonalEncrypt.setEncryptionMode(DiagonalEncrypt.EncryptionMode.CBC);
            else
                diagonalEncrypt.setEncryptionMode(DiagonalEncrypt.EncryptionMode.CTR);

            try {
                File outputFile = diagonalEncrypt.encrypt(file);

                BufferedReader reader = new BufferedReader(new FileReader(outputFile));

                ciphertext.setText("");
                int result = reader.read();
                while (result > 0) {
                    ciphertext.setText(ciphertext.getText() + (char)result);
                    result = reader.read();
                }
                reader.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }


        });

        decryptBtn.setOnAction(event -> {
            int[][] keys = new int[4][9];
            int[] blockKey = new int[19];

            // reset key error messages
            for (int i = 0; i < lblDecryptErrorMessages.length; i++)
                lblDecryptErrorMessages[i].setText("");

            boolean isKeysValid = true;
            ArrayList<Integer> checkedValues = new ArrayList<>();
            // check key 1
            for (int i = 0; i < keyParts1Decrypt.length; i++) {
                int value = keyParts1Decrypt[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblDecryptErrorMessages[0].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                keys[0][i] = checkedValues.get(i);
            checkedValues.clear();

            // check key 2
            for (int i = 0; i < keyParts2Decrypt.length; i++) {
                int value = keyParts2Decrypt[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblDecryptErrorMessages[1].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                keys[1][i] = checkedValues.get(i);
            checkedValues.clear();

            // check key 3
            for (int i = 0; i < keyParts3Decrypt.length; i++) {
                int value = keyParts3Decrypt[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblDecryptErrorMessages[2].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                keys[2][i] = checkedValues.get(i);
            checkedValues.clear();

            // check key 4
            for (int i = 0; i < keyParts4Decrypt.length; i++) {
                int value = keyParts4Decrypt[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblDecryptErrorMessages[3].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                keys[3][i] = checkedValues.get(i);
            checkedValues.clear();

            // check block key
            for (int i = 0; i < keyParts5Decrypt.length; i++) {
                int value = keyParts5Decrypt[i].getSelectionModel().getSelectedItem();
                if (checkedValues.contains(value)) {
                    lblDecryptErrorMessages[4].setText(errorMessage);
                    isKeysValid = false;
                }
                else
                    checkedValues.add(value);
            }
            for (int i = 0; i < checkedValues.size(); i++)
                blockKey[i] = checkedValues.get(i);
            checkedValues.clear();

            // if a key is not valid do not run program
            if (!isKeysValid)
                return;

            File file = new File(dec.getText());
            DiagonalEncrypt diagonalEncrypt = new DiagonalEncrypt(keys, blockKey);

            if (algorithm.getSelectionModel().getSelectedItem().equals("ECB"))
                diagonalEncrypt.setEncryptionMode(DiagonalEncrypt.EncryptionMode.ECB);
            else if (algorithm.getSelectionModel().getSelectedItem().equals("CBC"))
                diagonalEncrypt.setEncryptionMode(DiagonalEncrypt.EncryptionMode.CBC);
            else
                diagonalEncrypt.setEncryptionMode(DiagonalEncrypt.EncryptionMode.CTR);

            try {
                File outputFile = diagonalEncrypt.decrypt(file);

                BufferedReader reader = new BufferedReader(new FileReader(outputFile));

                plain.setText("");
                int result = reader.read();
                while (result > 0) {
                    plain.setText(plain.getText() + (char)result);
                    result = reader.read();
                }
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        });
        decryptBtn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        decryptBtn.setEffect(null);
                    }
                });
        clear.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        clear.setEffect(shadow);
                    }
                });
        clear.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        clear.setEffect(null);
                    }
                });
        clear.setOnAction(e-> {
            text.clear();
            ciphertext.clear();
            dec.clear();
            plain.clear();
            key1.clear();
            key2.clear();
            key3.clear();
            key4.clear();
            key5.clear();
            dkey1.clear();
            dkey2.clear();
            dkey3.clear();
            dkey4.clear();
            dkey5.clear();

            // reset combobox values
            ComboBox[][] keyParts = new ComboBox[8][9];
            keyParts[0] = keyParts1;
            keyParts[1] = keyParts2;
            keyParts[2] = keyParts3;
            keyParts[3] = keyParts4;
            keyParts[4] = keyParts1Decrypt;
            keyParts[5] = keyParts2Decrypt;
            keyParts[6] = keyParts3Decrypt;
            keyParts[7] = keyParts4Decrypt;

            for (int i = 0; i < keyParts.length; i++) {
                for (int j = 0; j < keyParts[i].length; j++) {
                    if (j <= 4)
                        keyParts[i][j].getSelectionModel().select(0);
                    else
                        keyParts[i][j].getSelectionModel().select(1);
                }
            }

            ComboBox[][] blockKeyParts = new ComboBox[2][19];
            blockKeyParts[0] = keyParts5;
            blockKeyParts[1] = keyParts5Decrypt;

            for (int i = 0; i < blockKeyParts.length; i++) {
                for (int j = 0; j < blockKeyParts[i].length; j++) {
                    if (j <= 9)
                        blockKeyParts[i][j].getSelectionModel().select(0);
                    else
                        blockKeyParts[i][j].getSelectionModel().select(1);
                }
            }
        });


        //Pane for the layout
        BorderPane label = new BorderPane();
        label.setTop(lbl);
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setStyle("-fx-background-color: #7A212E");
        label.setRight(imgView);
        label.setAlignment(imgView, Pos.CENTER_RIGHT);
        label.setBottom(signature);
        label.setAlignment(signature,Pos.BASELINE_RIGHT);


        GridPane top = new GridPane();
        label.setLeft(top);
        top.add(text, 0, 1);

        GridPane root = new GridPane();
        //top.setCenter(root);
        label.setLeft(root);
        root.setCenterShape(true);
        root.setPadding(new Insets(0, 5, 5, 0));
        root.setHgap(5);
        root.setVgap(5);

        // encrypt Section
        root.add(lbl, 0, 0);
        root.add(text, 1, 0);
        root.add(algorithm, 2, 0);

        root.add(key1lbl, 0, 1);
        root.add(hBoxKey1, 1, 1);

        root.add(key2lbl, 0, 2);
        root.add(hBoxKey2, 1, 2);

        root.add(key3lbl, 0, 3);
        root.add(hBoxKey3, 1, 3);

        root.add(key4lbl, 0, 4);
        root.add(hBoxKey4, 1, 4);

        root.add(key5lbl, 0, 5);
        root.add(hBoxKey5, 1, 5);

        root.add(encryptBtn, 1, 6);

        root.add(resultlbl, 0, 7);
        root.add(ciphertext, 1, 7);

        root.add(space, 0, 8);

        // add error message text
        for (int i = 0, row = 1; i < lblEncryptErrorMessages.length; i++, row++)
            root.add(lblEncryptErrorMessages[i], 2, row);

        // decrypt Section
        root.add(decodelbl, 0, 9);
        root.add(dec, 1, 9);

        root.add(dkey1lbl, 0, 10);
        root.add(hBoxKey1Decrypt, 1, 10);

        root.add(dkey2lbl, 0, 11);
        root.add(hBoxKey2Decrypt, 1, 11);

        root.add(dkey3lbl, 0, 12);
        root.add(hBoxKey3Decrypt, 1, 12);

        root.add(dkey4lbl, 0, 13);
        root.add(hBoxKey4Decrypt, 1, 13);

        root.add(dkey5lbl, 0, 14);
        root.add(hBoxKey5Decrypt, 1, 14);

        root.add(decryptBtn, 1,15);
        root.add(ptext, 0,16);
        root.add(plain, 1, 16);

        // add error message text
        for (int i = 0, row = 10; i < lblDecryptErrorMessages.length; i++, row++)
            root.add(lblDecryptErrorMessages[i], 2, row);

        // clear button
        root.add(clear, 0, 18);

        Scene scene = new Scene(label, 635, 600);

        primaryStage.setTitle("Diagonal Transposition Cipher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

