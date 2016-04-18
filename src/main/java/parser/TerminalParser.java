package parser;

import static java.util.Arrays.asList;

import com.sun.javafx.fxml.expression.Expression;
import org.codehaus.jparsec.*;
import org.codehaus.jparsec.misc.Mapper;


public class TerminalParser {
    private static final String[] OPERATORS = {
            "+", "-", "*", "/", "%", ">", "<", ":=", ">=", "<=", "!=", "==", "(", ")", "&&", "||", ";"
    };

    private static final String[] KEYWORDS = {
            "skip", "write", "read", "while", "do", "if", "then", "else"
    };

    static final Terminals TERMS =
            Terminals.operators(OPERATORS).words(Scanners.IDENTIFIER).keywords(asList(KEYWORDS)).build();

    static final Parser<?> TOKENIZER = Parsers.or(
            Terminals.DecimalLiteral.TOKENIZER, TERMS.tokenizer().cast());

    static Parser<Token> token(String token) {
        return TERMS.token(token);
    }

   static Parser<?> term(String term) {
        return Mapper._(TERMS.token(term));
    }
}
