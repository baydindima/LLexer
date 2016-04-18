package models;

import models.expressions.NumberExpression;

import java.util.Objects;
import java.util.function.BiFunction;

public enum Operator {
    PLUS("+", (i1, i2) -> i1 + i2),
    MINUS("-", (i1, i2) -> i1 - i2),
    MUL("*", (i1, i2) -> i1 * i2),
    DIV("/", (i1, i2) -> i1 / i2),
    MOD("%", (i1, i2) -> i1 % i2),
    EQ("==", (i1, i2) -> Objects.equals(i1, i2) ? 1 : 0),
    GT(">", (i1, i2) -> i1 > i2 ? 1 : 0),
    LT("<", (i1, i2) -> i1 < i2 ? 1 : 0),
    GE(">=", (i1, i2) -> i1 >= i2 ? 1 : 0),
    LE("<=", (i1, i2) -> i1 <= i2 ? 1 : 0),
    NE("!=", (i1, i2) -> !Objects.equals(i1, i2) ? 1 : 0),
    AND("&&", (i1, i2) -> i1 != 0 && i2 != 0 ? 1 : 0),
    OR("||", (i1, i2) -> i1 != 0 || i2 != 0 ? 1 : 0);

    public static boolean isRightZeroThanWrong(Operator op) {
        return op == DIV ||
                op == MOD;
    }

    public static boolean isLeftZeroThanZero(Operator op) {
        return  op == DIV ||
                op == MOD ||
                op == AND ||
                isZeroThanZero(op);
    }

    public static boolean isZeroThanZero(Operator op) {
        return op == MUL ;
    }

    public static boolean isZeroThanRemove(Operator op) {
        return op == OR ||
                op == PLUS;
    }

    public static boolean isNonZeroThanNonZero(Operator op) {
        return op == OR;
    }

    public static boolean isOneThanRemove(Operator op) {
        return op == MUL;
    }

    public static boolean isRightOneThanRemove(Operator op) {
        return isOneThanRemove(op) ||
                op == DIV;
    }

    private final String str;
    private final BiFunction<Integer, Integer, Integer> function;

    Operator(String str, BiFunction<Integer, Integer, Integer> fun) {
        this.str = str;
        this.function = fun;
    }

    public NumberExpression apply(NumberExpression n1, NumberExpression n2) {
        return new NumberExpression(String.valueOf(function.apply(n1.value, n2.value)));
    }

    @Override
    public String toString() {
        return str;
    }
}