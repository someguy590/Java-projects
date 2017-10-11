import java.util.ArrayList;

public class Project10 {
    public static void main(String[] args) {
        String[] delta = {
                "q0 a q1 _ R",
                "q0 _ q3 _ L",
                "q0 b q4 b R",
                "q0 X q0 X R",
                "q1 a q1 a R",
                "q1 b q2 X L",
                "q1 X q1 X R",
                "q1 _ q4 _ L",
                "q2 a q2 a L",
                "q2 X q2 X L",
                "q2 _ q0 _ R",
                "q2 b q4 b R"
        };
        TM turing = new TM(delta, "q0", "q3", "q4");
        turing.run("aabbbb");
    }
}

class TM {
    public String[] delta;
    public String q0;
    public String qa;
    public String qr;

    public TM(String[] delta, String q0, String qa, String qr) {
        this.delta = delta;
        this.q0 = q0;
        this.qa = qa;
        this.qr = qr;
    }

    public void run(String w) {
        ArrayList<String> tape = new ArrayList<>();

        tape.add(q0);
        for (char ch : w.toCharArray()) {
            String s = Character.toString(ch);
            tape.add(s);
        }

        // print initial tape
        for (String s : tape)
            System.out.print(s);
        System.out.println();
        tape.remove(0); // Remove q0 to deal with just input in tape

        // Current state information
        String currentState = q0;
        int inputIndex = 0;

        // Run machine
        boolean isHalt = false;
        while (!isHalt) {
            for (String transition : delta) { // Next transition

                // Parse transition string for state data, input, etc
                String[] t = transition.split(" ");
                
                String transCurrentState = t[0];
                String transInput = t[1];
                
                // Check if transition valid at current state/input
                if (!currentState.equals(transCurrentState) ||
                        !tape.get(inputIndex).equals(transInput))
                    continue;

                String nextState = t[2];
                String output = t[3];
                String dir = t[4];

                currentState = nextState; // Move to next state
                tape.set(inputIndex, output); // Write to tape

                // Move to next input
                if (dir.equals("L") && (inputIndex - 1) >= 0)
                    inputIndex--;

                if (dir.equals("R"))
                    inputIndex++;

                // Add in blank character to end
                if (inputIndex >= tape.size())
                    tape.add("_");

                // Display tape
                tape.add(inputIndex, currentState); // Add in current state
                for (String s : tape)
                    System.out.print(s);
                System.out.println();
                
                // Remove state to deal with only input
                tape.remove(inputIndex);

                // Halt accept or reject
                if (currentState.equals(qa)) {
                    System.out.println("ACCEPT");
                    isHalt = true;
                }
                if (currentState.equals(qr)) {
                    System.out.println("REJECT");
                    isHalt = true;
                }
            }
        }
    }
}
