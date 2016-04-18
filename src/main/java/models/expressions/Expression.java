package models.expressions;

public interface Expression {
    void printTree(int offset);

    Expression tryResolve();
}
