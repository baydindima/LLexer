import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ParserListener extends SimpleExpressionBaseListener {

    @Override
    public void visitTerminal(@NotNull TerminalNode node) {
        super.visitTerminal(node);
        Token symbol = node.getSymbol();
        int type = symbol.getType();
        String typeName;
        if (type > 0) {
            typeName = SimpleExpressionLexer.ruleNames[type - 1];
        } else {
            typeName = "UNDIFINED";
        }
        System.out.printf("Text: %10s, Start: %d, End: %d, Type: %s \n",
                node.getText(),
                symbol.getStartIndex(),
                symbol.getStopIndex(),
                typeName
        );

    }
}
