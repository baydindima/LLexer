package parser;

import models.*;
import models.statements.*;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parser.Reference;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.misc.Mapper;

import static parser.TerminalParser.term;
import static parser.TerminalParser.token;

public class StatementParser {

    private static final Reference<Statement> STATEMENT_REFERENCE = Parser.newReference();

    private static final Parser<Statement> ASSIGN = curry(AssignStatement.class)
            .sequence(
                    ExpressionParser.VARIABLE,
                    term(":="),
                    ExpressionParser.EXPRESSION_REFERENCE.lazy()
            );

    private static final Parser<Statement> IF = curry(IfStatement.class)
            .sequence(
                    term("if"),
                    ExpressionParser.EXPRESSION_REFERENCE.lazy(),
                    term("then"),
                    STATEMENT_REFERENCE.lazy(),
                    term("else"),
                    STATEMENT_REFERENCE.lazy()
            );

    private static final Parser<Statement> READ = curry(ReadStatement.class)
            .sequence(
                    term("read"),
                    ExpressionParser.EXPRESSION_REFERENCE.lazy()
            );

    private static final Parser<Statement> WRITE = curry(WriteStatement.class)
            .sequence(
                    term("write"),
                    ExpressionParser.EXPRESSION_REFERENCE.lazy()
            );

    private static final Parser<Statement> WHILE = curry(WhileStatement.class)
            .sequence(
                    term("while"),
                    ExpressionParser.EXPRESSION_REFERENCE.lazy(),
                    term("do"),
                    STATEMENT_REFERENCE.lazy()
            );

    private static final Parser<Statement> SKIP = curry(SkipStatement.class)
            .sequence(
                    token("skip")
            );

    private static final Parser<Statement> STATEMENT = Parsers.or(
            ASSIGN,
            IF,
            READ,
            WRITE,
            SKIP,
            WHILE
    );

    private static Mapper<Statement> curry(Class<? extends Statement> clazz, Object... args) {
        return Mapper.curry(clazz, args);
    }

    static final Parser<Void> IGNORED =
            Parsers.sequence(Scanners.WHITESPACES).skipMany();

    public static final Parser<Statement> SEQ_STATEMENT = Parsers.or(STATEMENT)
            .infixl(Mapper.<Statement>curry(SequenceStatement.class).infix(token(";")));

    static {
        STATEMENT_REFERENCE.set(SEQ_STATEMENT);
    }

    public static Program parse(String prog) {
        return Mapper.curry(Program.class).sequence(
                STATEMENT_REFERENCE.lazy()
        ).from(TerminalParser.TOKENIZER, IGNORED).parse(prog);
    }
}
