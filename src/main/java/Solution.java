
public class Solution {

    public static void main(String[] args) {
        SimpleExpressionParser parser = ParserFactory.getInstance("read x; if y + 1 == x then write y else skip; write x + 2");
        parser.addParseListener(new ParserListener());
        parser.program();
    }

}
