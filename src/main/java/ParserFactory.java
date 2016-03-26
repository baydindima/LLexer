
import org.antlr.v4.runtime.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ParserFactory {

    public static SimpleExpressionParser getInstance(String text) {
        return getInstance(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));
    }

    public static SimpleExpressionParser getInstance(InputStream inputStream) {
        SimpleExpressionLexer lexer;
        try {
            lexer = new SimpleExpressionLexer(
                    new ANTLRInputStream(inputStream)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SimpleExpressionParser parser = new SimpleExpressionParser(new CommonTokenStream(lexer));
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        return parser;
    }
}
