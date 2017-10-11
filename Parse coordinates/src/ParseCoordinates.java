import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseCoordinates {
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length <= 0) {
            System.out.println("Usage: java ParseCoordinates coordinatesFile.txt");
            System.exit(0);
        }

        File file = new File(args[0]);

        Scanner input = new Scanner(file);


        // print body of source file
        int robotCount = 1;
        while(input.hasNextLine()){
            String line = input.nextLine();

            String fileName = "main" + robotCount + ".c";
            PrintWriter output = new PrintWriter(fileName);

            // print first few header lines of source file
            printSourceFileStart(output);

            // print main body of source file
            printSourceFileMiddle(output, line);

            // print last few lines of source file
            printSourceFileEnd(output);

            output.close();
            robotCount++;
        }

    }

    private static void printSourceFileStart(PrintWriter output) {
        output.println("#include <pololu/3pi.h>");
        output.println("#include \"ArmstrongNavigation/ArmstrongNavigation.h\"");
        output.println();
        output.println("int main()");
        output.println("{");
        output.println("initialize();");
        output.println();
    }

    private static void printSourceFileMiddle(PrintWriter output, String coordinates) {
        ArrayList<ArrayList<Integer>> coordinateList = new ArrayList<>();
        int x = 0;
        int y = 0;

        int coordinateCount = 0; // track if currently parsing x or y coordinate
        // add coordinates to coordinate list
        for (int i = 0; i < coordinates.length(); i++) {

            // ignore non-digit characters
            if (!Character.isDigit(coordinates.charAt(i)))
                continue;

            // every even count of a numbers are x coordinates
            // every odd count of a number y coordinates
            if (coordinateCount++ % 2 == 0) {
                x = Character.getNumericValue(coordinates.charAt(i));
            }
            else {
                y = Character.getNumericValue(coordinates.charAt(i));
                ArrayList<Integer> coordinatePair = new ArrayList<>();
                coordinatePair.add(x);
                coordinatePair.add(y);
                coordinateList.add(coordinatePair);
            }
        }

        // print start and end location to LCD
        int startX = coordinateList.get(0).get(0);
        int startY = coordinateList.get(0).get(1);

        output.println("print(\"(" + startX + ", " + startY + ")->\");");
        output.println("lcd_goto_xy(0, 1);");
        output.println("print(\"(" + x + ", " + y + ")\");");
        output.println();

        // let user press button before letting robot run
        output.println("wait_for_button_press(BUTTON_B);");
        output.println("wait_for_button_release(BUTTON_B);");
        output.println("delay_ms(500);");
        output.println();

        // set robot start location
        output.println("r3PI.x = " + startX + ";");
        output.println("r3PI.y = " + startY + ";");
        output.println();

        // make robot go to coordinates
        for (ArrayList<Integer> coordinatePair : coordinateList) {
            x = coordinatePair.get(0);
            y = coordinatePair.get(1);
            output.println("gotoPoint(" + x + ", " + y + ");");
        }
        output.println();

    }

    private static void printSourceFileEnd(PrintWriter output) {
        output.println("while(1);");
        output.println("}");
    }
}
