package models.statements;

import org.codehaus.jparsec.Token;

public class SequenceStatement implements Statement {
    private final Statement leftExpression;
    private final String col;
    private final Statement rightExpression;

    public SequenceStatement(Statement leftExpression, Token col, Statement rightExpression) {
        this.leftExpression = leftExpression;
        this.col = col.toString();
        this.rightExpression = rightExpression;
    }

    @Override
    public String toString() {
        return leftExpression + "; " + rightExpression;
    }

    @Override
    public void printProgram(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s;", "", leftExpression));
        rightExpression.printProgram(offset);
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", "SequenceStatement"));
        leftExpression.printTree(offset + 1);
        rightExpression.printTree(offset + 1);
    }
}
