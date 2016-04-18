package models.statements;

import models.expressions.Expression;

public class WriteStatement implements Statement{
    private final Expression expression;

    public WriteStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "write " + expression;
    }

    @Override
    public void printProgram(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s write %s", "", expression));
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", "WriteStatement"));
        expression.printTree(offset + 1);
    }
}
