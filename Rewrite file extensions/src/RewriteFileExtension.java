import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RewriteFileExtension {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: RewriteFileExtension directory extension");
            System.exit(1);
        }

        File directory = new File(args[0]);

        if (!directory.isDirectory()) {
            System.out.println("Directory not entered");
            System.exit(1);
        }

        for (File f : directory.listFiles()) {
            if (f.isDirectory())
                continue;

            String oldName = f.getName();
            int index = oldName.lastIndexOf('.');
            String newName = oldName.substring(0, index + 1) + args[1];

            Files.move(f.toPath(), (new File(newName).toPath()));
        }

    }
}
