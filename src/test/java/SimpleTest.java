import org.junit.Test;


public class SimpleTest {

    @Test
    public void testArithmetic() throws Exception {
        SimpleExpressionParser parser = ParserFactory.getInstance("1+2+3");
        parser.arithmetic_expr();
    }

    @Test
    public void testLogic() throws Exception {
        SimpleExpressionParser parser = ParserFactory.getInstance("1 > 2");
        parser.logical_expr();
    }

    @Test
    public void testIfStatement() throws Exception {
        SimpleExpressionParser parser = ParserFactory.getInstance("if 1 == 2 then x := 5 else skip");
        parser.if_statement();
    }

    @Test
    public void testWhileStatement() throws Exception {
        SimpleExpressionParser parser = ParserFactory.getInstance("while 1 == 2 do read x");
        parser.while_statement();
    }

    @Test
    public void testProgram() throws Exception {
        SimpleExpressionParser parser = ParserFactory.getInstance("read x; if y + 1 == x then write y else skip");
        parser.program();
    }

    @Test
    public void testProgram2() throws Exception {
        SimpleExpressionParser parser = ParserFactory.getInstance("read x; while 1 == 2 do read x; read x ");
        parser.program();
    }

}
