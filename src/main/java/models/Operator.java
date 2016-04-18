package models;

import models.expressions.NumberExpression;

import java.util.Objects;
import java.util.function.BiFunction;

public enum Operator {
    PLUS("+", (i2, i1) -> i1 + i2),
    MINUS("-", (i2, i1) -> i1 - i2),
    MUL("*", (i2, i1) -> i1 * i2),
    DIV("/", (i2, i1) -> i1 / i2),
    MOD("%", (i2, i1) -> i1 % i2),
    EQ("==", (i2, i1) -> Objects.equals(i1, i2) ? 1 : 0),
    GT(">", (i2, i1) -> i1 > i2 ? 1 : 0),
    LT("<", (i2, i1) -> i1 < i2 ? 1 : 0),
    GE(">=", (i2, i1) -> i1 >= i2 ? 1 : 0),
    LE("<=", (i2, i1) -> i1 <= i2 ? 1 : 0),
    NE("!=", (i2, i1) -> !Objects.equals(i1, i2) ? 1 : 0),
    AND("&&", (i2, i1) -> i1 != 0 && i2 != 0 ? 1 : 0),
    OR("||", (i2, i1) -> i1 != 0 || i2 != 0 ? 1 : 0);

    public static boolean isZeroThanZero(Operator op) {
        return op == MUL ||
                op == DIV ||
                op == MOD ||
                op == AND;
    }

    public static boolean isZeroThanRemove(Operator op) {
        return op == OR ||
                op == PLUS;
    }

    public static boolean isNonZeroThanNonZero(Operator op) {
        return op == OR;
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