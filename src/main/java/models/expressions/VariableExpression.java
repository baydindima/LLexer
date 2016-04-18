package models.expressions;

public class VariableExpression implements Expression {
    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s %s", "", "VariableExpression", name));
    }

    @Override
    public Expression tryResolve() {
        return this;
    }
}
