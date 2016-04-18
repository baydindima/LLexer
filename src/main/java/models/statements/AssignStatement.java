package models.statements;

import models.expressions.Expression;
import models.expressions.VariableExpression;

public class AssignStatement implements Statement {
    private final VariableExpression value;
    private final Expression expression;

    public AssignStatement(VariableExpression value, Expression expression) {
        this.value = value;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return value + " := " + expression;
    }

    @Override
    public void printProgram(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s := %s", "", value, expression));
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", "AssignStatement"));
        value.printTree(offset + 1);
        expression.printTree(offset + 1);
    }
}
