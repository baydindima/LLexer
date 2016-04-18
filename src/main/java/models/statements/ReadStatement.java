package models.statements;

import models.expressions.Expression;

public class ReadStatement implements Statement {
    private Expression expression;

    public ReadStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "read " + expression;
    }

    @Override
    public void printProgram(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s read %s", "", expression));
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", "ReadStatement"));
        expression.printTree(offset + 1);
    }

    @Override
    public Statement tryResolve() {
        expression = expression.tryResolve();
        return this;
    }

}
