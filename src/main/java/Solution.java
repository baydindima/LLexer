import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Solution {

    public static void main(String[] args) {
        String str = args[0];
        ANTLRInputStream input = new ANTLRInputStream(str);
        SimpleExpressionLexer lexer = new SimpleExpressionLexer(input);
        List<? extends Token> allTokens = lexer.getAllTokens();
        for (Token symbol : allTokens) {
            int type = symbol.getType();
            String typeName;
            if (type > 0) {
                typeName = SimpleExpressionLexer.ruleNames[type - 1];
            } else {
                typeName = "UNDIFINED";
            }
            System.out.printf("Text: %10s, Start: %d, End: %d, Type: %s \n",
                    symbol.getText(),
                    symbol.getStartIndex(),
                    symbol.getStopIndex(),
                    typeName
            );
        }
    }

}
