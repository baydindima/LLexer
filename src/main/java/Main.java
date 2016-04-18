import models.Program;
import parser.StatementParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String str;
        if (args.length == 0) {
            str =  new String(Files.readAllBytes(Paths.get("./test.txt")));
        } else {
            str = args[0];
        }


        Program program = StatementParser.parse(str);
        System.out.println("Tree:");
        program.printTree();
        System.out.println("Program printer:");
        program.printProgram();
        program.tryResolve();
        System.out.println("Simplified program:");
        System.out.println("Tree:");
        program.printTree();
        System.out.println("Program printer:");
        program.printProgram();
    }

}
