package models.statements;


import models.expressions.Expression;
import models.expressions.NumberExpression;
import org.codehaus.jparsec.Token;

public class WhileStatement implements Statement {
    private Expression condition;
    private final Statement statement;

    public WhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "while " + condition + " do " + statement;
    }

    @Override
    public void printProgram(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s while %s do", "", condition));
        statement.printProgram(offset + 1);
    }


    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", "WhileStatement"));
        condition.printTree(offset + 1);
        statement.printTree(offset + 1);
    }

    @Override
    public Statement tryResolve() {
        condition = condition.tryResolve();
        if (condition instanceof NumberExpression) {
            NumberExpression condition = (NumberExpression) this.condition;
            if (condition.value == 0) {
                return new SkipStatement(new Token(0, 0, ""));
            } else {
                return statement.tryResolve();
            }
        }
        return this;
    }
}
