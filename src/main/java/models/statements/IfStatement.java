package models.statements;

import models.expressions.Expression;
import models.expressions.NumberExpression;

public class IfStatement implements Statement {
    private Expression condition;
    private final Statement thenExpression;
    private final Statement elseExpression;

    public IfStatement(Expression condition, Statement thenExpression, Statement elseExpression) {
        this.condition = condition;
        this.thenExpression = thenExpression;
        this.elseExpression = elseExpression;
    }

    @Override
    public String toString() {
        return "if " + condition + " then " + thenExpression + " else " + elseExpression;
    }

    @Override
    public void printProgram(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s if %s then", "", condition));
        thenExpression.printProgram(offset + 1);
        System.out.println(String.format("%" + (offset * 3) + "s else", ""));
        elseExpression.printProgram(offset + 1);
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", "IfStatement"));
        thenExpression.printTree(offset + 1);
        elseExpression.printTree(offset + 1);
    }

    @Override
    public Statement tryResolve() {
        condition = condition.tryResolve();
        if (condition instanceof NumberExpression) {
            NumberExpression condition = (NumberExpression) this.condition;
            if (condition.value == 0) {
                return elseExpression.tryResolve();
            } else {
                return thenExpression.tryResolve();
            }
        }
        return this;
    }
}
