import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Jonathan Moyett
 * Chris West
 * Dante
 *
 * Main interface for interacting with system. You assume the role of a user
 * with the ability to send encrypted messages through the DES or VEA algorithms
 * or to decrypt these messages.
 *
 * Images are enrypted and decrypted with DES when they are sent peer-to-peer
 * Images are encrypted and decrypted with VEA when they are to be broadcasted to all users
 */
public class GUI_Interface extends Application {
    private static final int COKE_IMAGE_BYTES = 99950;

    ArrayList<User> userList = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        // Users of the system; assume you are john
        User john = new User(0);
        User velma = new User(1);
        User brady = new User(2);
        userList.add(john);
        userList.add(velma);
        userList.add(brady);

        // Create a pane and set its properties
        HBox root = new HBox();
        root.setSpacing(15);
        root.widthProperty().divide(2);
        root.heightProperty().divide(2);
        GridPane Lpane = new GridPane();
        Lpane.setHgap(5.5);
        Lpane.setVgap(3.5);
        VBox Rpane = new VBox();
        root.getChildren().addAll(Lpane, Rpane);
        
        // Place nodes in Lpane
        Label DVlbl = new Label(" Choose Encryption ");
        ComboBox<String> DVcombo = new ComboBox<>();
        DVcombo.getItems().addAll("DES", "VEA");
        DVcombo.setValue(DVcombo.getItems().get(0));

        Label lblMode = new Label(" Choose mode ");
        ComboBox<String> comboMode = new ComboBox<>();
        comboMode.getItems().addAll("ECB", "CBC");
        comboMode.setValue(comboMode.getItems().get(0));

        Label Roundslbl = new Label(" Rounds (no more than 16):");
        TextField Roundstxt = new TextField ("");

        Label DHParam = new Label(" DH Key Paramaters ");
        TextField DHtxt = new TextField ("");
        Label VEAParam = new Label(" VEA Key Paramaters ");
        TextField VEAtxt = new TextField ("");
        Label Keysz = new Label(" Key Size ");
        TextField KStxt = new TextField ("");
        Label Systp = new Label(" System Throughput (Bytes/s) ");
        TextField SYSTPtxt = new TextField ("");
        SYSTPtxt.setDisable(true);
        Label RSAtp = new Label(" RSA E/D Throughput");
        TextField RSATPtxt = new TextField ("");
        Label DHtp = new Label(" DH E/D Throughput ");
        TextField DHTPtxt = new TextField ("");
        Label PKtp = new Label(" Public Key ");
        TextField PKtxt = new TextField ("");

        Label People = new Label(" Number of People ");
        TextField userCounttxt = new TextField("3");
        userCounttxt.setDisable(true);

        Label Person = new Label(" Which person? ");
        ComboBox<String> Personcombo = new ComboBox<>();
        Personcombo.getItems().addAll("John", "Velma", "Brady", "All Users");
        Personcombo.setValue(Personcombo.getItems().get(0));

        DVcombo.setOnAction(e -> {
            int index = Personcombo.getItems().size() - 1;
            String string = Personcombo.getItems().get(index);

            if (DVcombo.getValue().equals("DES"))
                Personcombo.setValue(Personcombo.getItems().get(0));
            if (DVcombo.getValue().equals("VEA"))
                Personcombo.setValue(string);
        });

        Personcombo.setOnAction(e -> {
            int index = Personcombo.getItems().size() - 1;
            String string = Personcombo.getItems().get(index);

            if (Personcombo.getValue().equals(string))
                DVcombo.setValue("VEA");
            else
                DVcombo.setValue("DES");
        });

        Button btnEncrypt = new Button("Encrypt");
        Button btnDecrypt = new Button("Decrypt");

        // Warns user to decrypt image before continuing encryption
        Label lblEncryptWarn = new Label();
        lblEncryptWarn.setWrapText(true);

        Lpane.add(DVlbl, 0, 0);
        Lpane.add(DVcombo, 1, 0);

        Lpane.add(lblMode, 0, 1);
        Lpane.add(comboMode, 1, 1);

        Lpane.add(Roundslbl, 0, 2);
        Lpane.add(Roundstxt, 1, 2);

        Lpane.add(DHParam, 0, 3);
        Lpane.add(DHtxt, 1, 3);
        Lpane.add(VEAParam, 0, 4);
        Lpane.add(VEAtxt, 1, 4);
        Lpane.add(Keysz, 0, 5);
        Lpane.add(KStxt, 1, 5);
        Lpane.add(Systp, 0, 6);
        Lpane.add(SYSTPtxt, 1, 6);
        Lpane.add(RSAtp, 0, 7);
        Lpane.add(RSATPtxt, 1, 7);
        Lpane.add(DHtp, 0, 8);
        Lpane.add(DHTPtxt, 1, 8);
        Lpane.add(PKtp, 0, 9);
        Lpane.add(PKtxt, 1, 9);
        Lpane.add(People, 0, 10);
        Lpane.add(userCounttxt, 1, 10);
        Lpane.add(Person, 0, 11);
        Lpane.add(Personcombo, 1, 11);
        Lpane.add(btnEncrypt, 0, 12);
        Lpane.add(btnDecrypt, 1, 12);
        Lpane.add(lblEncryptWarn, 0, 13);

        
        // Place nodes in Rpane
        Image cokeECBImage = john.getPublicImage();
        Image cokeCBCImage = john.getPublicImage();
        ImageView pepsi = new ImageView(john.getSecretImage());

        ImageView cokeECBImageView = new ImageView(cokeECBImage);
        ImageView cokeCBCImageView = new ImageView(cokeCBCImage);

        Label ECBlbl = new Label("Image Using ECB ");
        Label CBClbl = new Label("Image Using CBC ");
        Label SecMesslbl = new Label("Secret Message ");
        Rpane.getChildren().addAll(
                ECBlbl, cokeECBImageView,
                CBClbl, cokeCBCImageView,
                SecMesslbl, pepsi);

        // Set action for buttons
        btnEncrypt.setOnAction(e -> {
            long startTime = System.nanoTime(); // Determine throughput

            String encryptionAlgo = DVcombo.getValue();
            if (encryptionAlgo.equals("DES")) {
                try {
                    if (comboMode.getValue().equals("ECB"))
                        john.encryptDES(DES.ECB_MODE);
                    else
                        john.encryptDES(DES.CBC_MODE);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (encryptionAlgo.equals("VEA"))
                john.encryptVEA();

            for (User u : userList)
                u.readFromBuffer();

            if (comboMode.getValue().equals("ECB"))
                cokeECBImageView.setImage(john.getPublicImage());
            else
                cokeCBCImageView.setImage(john.getPublicImage());
            lblEncryptWarn.setText("Decrypt image before encrypting again");
            btnEncrypt.setDisable(true);
            DVcombo.setDisable(true);
            comboMode.setDisable(true);

            long endTime = System.nanoTime() - startTime;
            double throughput = COKE_IMAGE_BYTES / ((double)endTime / 1E9);
            SYSTPtxt.setText(String.valueOf(throughput));

        });


        btnDecrypt.setOnAction(e -> {
            // Apply selected encryption
            String encryptionAlgo = DVcombo.getValue();
            if (encryptionAlgo.equals("DES")) {
                try {
                    if (comboMode.getValue().equals("ECB"))
                        john.decryptDES(DES.ECB_MODE);
                    else
                        john.decryptDES(DES.CBC_MODE);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (encryptionAlgo.equals("VEA"))
                john.decryptVEA();

            // Inform users new data in buffer
            for (User u : userList)
                u.readFromBuffer();

            // Update interface images
            if (comboMode.getValue().equals("ECB"))
                cokeECBImageView.setImage(john.getPublicImage());
            else
                cokeCBCImageView.setImage(john.getPublicImage());
            lblEncryptWarn.setText("");
            btnEncrypt.setDisable(false);
            DVcombo.setDisable(false);
            comboMode.setDisable(false);
        });
        
         // Setting the scene
        Scene scene = new Scene(root, 550, 500);
        primaryStage.setTitle("Image Share");
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
