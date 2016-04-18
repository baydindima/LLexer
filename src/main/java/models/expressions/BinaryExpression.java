package models.expressions;

import models.Operator;

public class BinaryExpression implements Expression {
    private Expression left;
    private Expression right;
    private final Operator operator;

    public BinaryExpression(Expression left, Operator op, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = op;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", left, operator, right);
    }

    @Override
    public void printTree(int offset) {
        System.out.println(String.format("%" + (offset * 3) + "s %s", "", operator.name()));
        left.printTree(offset + 1);
        right.printTree(offset + 1);
    }

    @Override
    public Expression tryResolve() {
        left = left.tryResolve();
        if (left instanceof NumberExpression) {
            NumberExpression number = (NumberExpression) left;
            if (number.value == 0) {
                if (Operator.isLeftZeroThanZero(operator)) {
                    return new NumberExpression("0");
                }
                if (Operator.isZeroThanRemove(operator)) {
                    return right.tryResolve();
                }
            } else {
                if (Operator.isNonZeroThanNonZero(operator)) {
                    return left;
                }
                if (number.value == 1 && Operator.isOneThanRemove(operator)) {
                    return right.tryResolve();
                }
            }
        }
        right = right.tryResolve();
        if (right instanceof NumberExpression) {
            NumberExpression number = (NumberExpression) right;
            if (number.value == 0) {
                if (Operator.isZeroThanZero(operator)) {
                    return new NumberExpression("0");
                }
                if (Operator.isZeroThanRemove(operator)) {
                    return left;
                }
                if (Operator.isRightZeroThanWrong(operator)) {
                    throw new ArithmeticException("Divide by Zero!");
                }
            } else {
                if (Operator.isNonZeroThanNonZero(operator)) {
                    return right;
                }
                if (number.value == 1 && Operator.isRightOneThanRemove(operator)) {
                    return left;
                }
            }
        }
        if (left instanceof NumberExpression && right instanceof NumberExpression) {
            NumberExpression number1 = (NumberExpression) left;
            NumberExpression number2 = (NumberExpression) right;
            return operator.apply(number1, number2);
        }
        return this;
    }
}
