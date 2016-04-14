
public class Solution {

    public static void main(String[] args) {
        System.out.println(args[0]);
        SimpleExpressionParser parser = ParserFactory.getInstance(args[0]);
        parser.addParseListener(new ParserListener());
        parser.program();
    }

}
