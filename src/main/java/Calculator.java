import org.codehaus.jparsec.*;
import org.codehaus.jparsec.functors.Binary;
import org.codehaus.jparsec.functors.Unary;


public class Calculator {

    enum BinaryOperator implements Binary<Integer> {
        PLUS {
            public Integer map(Integer a, Integer b) {
                return a + b;
            }
        },
        MINUS {
            public Integer map(Integer a, Integer b) {
                return a - b;
            }
        },
        MUL {
            public Integer map(Integer a, Integer b) {
                return a * b;
            }
        },
        DIV {
            public Integer map(Integer a, Integer b) {
                return a / b;
            }
        }
    }

    enum UnaryOperator implements Unary<Integer> {
        NEG {
            public Integer map(Integer n) {
                return -n;
            }
        }
    }

    static final Parser<Integer> NUMBER = Terminals.DecimalLiteral.PARSER.map(Integer::valueOf);

    private static final Terminals OPERATORS = Terminals.operators("+", "-", "*", "/", "(", ")");

    static final Parser<Void> IGNORED =
            Parsers.or(Scanners.JAVA_LINE_COMMENT, Scanners.JAVA_BLOCK_COMMENT, Scanners.WHITESPACES).skipMany();

    static final Parser<?> TOKENIZER =
            Parsers.or(Terminals.DecimalLiteral.TOKENIZER, OPERATORS.tokenizer());

    static Parser<?> term(String... names) {
        return OPERATORS.token(names);
    }

    static final Parser<BinaryOperator> WHITESPACE_MUL =
            term("+", "-", "*", "/").not().retn(BinaryOperator.MUL);

    static <T> Parser<T> op(String name, T value) {
        return term(name).retn(value);
    }

    static Parser<Integer> calculator(Parser<Integer> atom) {
        Parser.Reference<Integer> ref = Parser.newReference();
        Parser<Integer> unit = ref.lazy().between(term("("), term(")")).or(atom);
        Parser<Integer> parser = new OperatorTable<Integer>()
                .infixl(op("+", BinaryOperator.PLUS), 10)
                .infixl(op("-", BinaryOperator.MINUS), 10)
                .infixl(op("*", BinaryOperator.MUL).or(WHITESPACE_MUL), 20)
                .infixl(op("/", BinaryOperator.DIV), 20)
                .prefix(op("-", UnaryOperator.NEG), 30)
                .build(unit);
        ref.set(parser);
        return parser;
    }

    public static final Parser<Integer> CALCULATOR = calculator(NUMBER).from(TOKENIZER, IGNORED);
}