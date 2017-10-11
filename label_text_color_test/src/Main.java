/**
 * Created by someguy590 on 3/18/2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    public String[] keywords = {"public", "class", "if"};
    @Override
    public void start(Stage primaryStage) {
        String question = "28. Suppose the input for number is 9. What is the output from running the following program?\n" +
                "\n" +
                "import java.util.Scanner;\n" +
                "\n" +
                "public class Test {\n" +
                "  public static void main(String[] args) {\n" +
                "    Scanner input = new Scanner(System.in);\n" +
                "    System.out.print(\"Enter an integer: \");\n" +
                "    int number = input.nextInt();   \n" +
                "\n" +
                "    int i;\n" +
                "\n" +
                "    boolean isPrime = true;\n" +
                "    for (i = 2; i < number && isPrime; i++) {\n" +
                "      if (number % i == 0) {\n" +
                "        isPrime = false;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    System.out.println(\"i is \" + i);\n" +
                "\n" +
                "    if (isPrime)\n" +
                "      System.out.println(number + \" is prime\");\n" +
                "    else\n" +
                "      System.out.println(number + \" is not prime\");   \n" +
                "  }\n" +
                "}";
        TextFlow textFlow = new TextFlow();

//        Scanner scanner = new Scanner(question);
        System.out.println(Arrays.toString(question.split("(?<!^|[\"a-zA-Z'])|(?![\"a-zA-Z'])")));
        String[] words = question.split("(?<!^|[a-zA-Z'])|(?![a-zA-Z'])");
        ArrayList<Text> texts = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].contains("\"")) {
                if (words[i].matches("\".*\"")) {
                    texts.add(new Text(words[i]));
                    continue;
                }

                String string = words[i];
                i++;
                while (!words[i].equals("\"")) {
                    string += words[i++];
                }
                string += words[i];
                System.out.println(string);
                texts.add(new Text(string));
            }
            else
                texts.add(new Text(words[i]));
        }

        for (int i = 0; i < texts.size(); i++) {
            if (isKeyword(texts.get(i).getText()))
                texts.get(i).setFill(Color.GREEN);

            if (texts.get(i).getText().matches("\".*\""))
                texts.get(i).setFill(Color.BLUE);

            textFlow.getChildren().add(texts.get(i));
        }
//        System.out.println(Arrays.toString("28. \"abc\".compareTo(\"aba\") \n \t returns ______".split("(?<=\\s)|(?=\\s)|(?<=(\".{0,1000}\"))|(?=(\".*\"))")));

        BorderPane root = new BorderPane();
        root.setCenter(textFlow);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean isKeyword(String word) {
        for (String keyword : keywords) {
            if (word.equals(keyword))
                return true;
        }

        return false;
    }
}
