package models.expressions;

public class NumberExpression implements Expression {
    public final int value;

    public NumberExpression(String value) {
        this.value = Integer.valueOf(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s %d", "", "NumberExpression", value));
    }

    @Override
    public Expression tryResolve() {
        return this;
    }
}
