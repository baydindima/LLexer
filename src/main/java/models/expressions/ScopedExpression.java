package models.expressions;

public class ScopedExpression implements Expression{
    private Expression innerExpression;

    public ScopedExpression(Expression innerExpression) {
        this.innerExpression = innerExpression;
    }

    @Override
    public String toString() {
        return String.format("(%s)", innerExpression);
    }

    @Override
    public void printTree(int offset) {
        innerExpression.printTree(offset);
    }

    @Override
    public Expression tryResolve() {
        innerExpression = innerExpression.tryResolve();
        if (innerExpression instanceof NumberExpression
                || innerExpression instanceof VariableExpression
                || innerExpression instanceof ScopedExpression) {
            return innerExpression;
        }
        return this;
    }
}
