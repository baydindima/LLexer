package parser;

import models.*;
import models.expressions.*;
import org.codehaus.jparsec.OperatorTable;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parser.Reference;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.functors.Binary;
import org.codehaus.jparsec.misc.Mapper;

import static parser.TerminalParser.term;

public class ExpressionParser {

    static final Reference<Expression> EXPRESSION_REFERENCE = Parser.newReference();

    static final Parser<Expression> VARIABLE = curry(VariableExpression.class).sequence(Terminals.Identifier.PARSER);

    static final Parser<Expression> NUMBER = curry(NumberExpression.class).sequence(Terminals.DecimalLiteral.PARSER);

    static final Parser<Expression> SCOPED = curry(ScopedExpression.class).sequence(
            term("("),
            EXPRESSION_REFERENCE.lazy(),
            term(")")
    );

//    static <T> Parser<T> paren(Parser<T> parser) {
//        return parser.between(term("("), term(")"));
//    }

    static Parser<Expression> expression(Parser<Expression> cond) {
        Reference<Expression> reference = Parser.newReference();
        Parser<Expression> atom = Parsers.or(
                VARIABLE, NUMBER, arithmetic(cond));
        Parser<Expression> expression = arithmetic(atom).label("expression");
        reference.set(expression);
        return expression;
    }

    static Parser<Expression> arithmetic(Parser<Expression> atom) {
        Reference<Expression> reference = Parser.newReference();
        Parser<Expression> parser = new OperatorTable<Expression>()
                .infixl(binary("+", Operator.PLUS), 10)
                .infixl(binary("-", Operator.MINUS), 10)
                .infixl(binary("*", Operator.MUL), 20)
                .infixl(binary("/", Operator.DIV), 20)
                .infixl(binary("%", Operator.MOD), 20)
                .infixl(binary(">", Operator.GT), 10)
                .infixl(binary("<", Operator.LT), 10)
                .infixl(binary("==", Operator.EQ), 10)
                .infixl(binary(">=", Operator.GE), 10)
                .infixl(binary("<=", Operator.LE), 10)
                .infixl(binary("!=", Operator.NE), 10)
                .infixl(binary("&&", Operator.AND), 20)
                .infixl(binary("||", Operator.OR), 10)
                .build(SCOPED.or(atom)).label("arithmetic expression");
        reference.set(parser);
        return parser;
    }

    private static Parser<Expression> compare(
            Parser<Expression> operand, String name, Operator op) {
        return curry(BinaryExpression.class).sequence(operand, term(name).retn(op), operand);
    }

    private static Parser<Binary<Expression>> binary(String name, Operator op) {
        return term(name).next(binaryExpression(op).binary());
    }

    private static Mapper<Expression> binaryExpression(Operator op) {
        return curry(BinaryExpression.class, op);
    }

    private static Mapper<Expression> curry(Class<? extends Expression> clazz, Object... args) {
        return Mapper.curry(clazz, args);
    }

    static {
        EXPRESSION_REFERENCE.set(expression(EXPRESSION_REFERENCE.lazy()));
    }

}
