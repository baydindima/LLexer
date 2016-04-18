package models.statements;


import models.expressions.Expression;

public class WhileStatement implements Statement {
    private final Expression condition;
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
}
