import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length <= 0) {
            System.out.println("Usage: java ParseCoordinates coordinatesFile.txt");
            System.exit(0);
        }

        File file = new File(args[0]);

        Scanner input = new Scanner(file);
        PrintWriter output = new PrintWriter("main.c");

        // print first few lines of source file
        output.println("#include <pololu/3pi.h>");
        output.println("#include \"ArmstrongNavigation/ArmstrongNavigation.h\"");
        output.println();
        output.println("int main()");
        output.println("{");
        output.println("initialize();");
        output.println();

        // print body of source file
        while(input.hasNextLine()){
            String line = input.nextLine();

            int x = 0;
            int y = 0;

            int coordinateCount = 0;
            for (int i = 0; i < line.length(); i++) {

                // ignore non-digit characters
                if (!Character.isDigit(line.charAt(i)))
                    continue;

                // first coordinate pair determines start area of robot
                if (coordinateCount == 0) {
                    coordinateCount++;
                    x = Character.getNumericValue(line.charAt(i));
                    output.println("r3PI.x = " + x + ";"); // sets start x of robot
                    continue;
                }

                if (coordinateCount == 1) {
                    coordinateCount++;
                    y = Character.getNumericValue(line.charAt(i));
                    output.println("r3PI.y = " + y + ";"); // sets start y of robot
                    output.println();
                    continue;
                }

                // every even count of a numbers are x coordinates
                // every odd count of a number y coordinates
                if (coordinateCount % 2 == 0) {
                    x = Character.getNumericValue(line.charAt(i));
                }
                else {
                    y = Character.getNumericValue(line.charAt(i));
                    output.println("gotoPoint(" + x + ", " + y + ");");
                }
                coordinateCount++;
            }
        }

        // print last few lines of source file
        output.println();
        output.println("while(1);");
        output.println("}");

        output.close();
    }
}
