import models.Program;
import parser.StatementParser;

public class Main {
    public static void main(String[] args) {
        Program program = StatementParser.parse(args[0]);
        System.out.println("Tree:");
        program.printTree();
        System.out.println("Program printer:");
        program.printProgram();
    }

}
