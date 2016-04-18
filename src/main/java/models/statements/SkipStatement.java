package models.statements;

import org.codehaus.jparsec.Token;

public class SkipStatement implements Statement {
    private final String skip;

    public SkipStatement(Token skip) {
        this.skip = skip.toString();
    }

    @Override
    public String toString() {
        return "skip";
    }

    @Override
    public void printProgram(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", toString()));
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", "SkipStatement"));
    }

    @Override
    public Statement tryResolve() {
        return this;
    }

}
