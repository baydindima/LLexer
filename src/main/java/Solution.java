import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.TreeViewer;

import javax.swing.*;
import java.util.Arrays;

public class Solution {
    private static final int height   = 500;
    private static final int width    = 1000;
    private static final double scale = 1.;

    public static void main(String[] args) {
        SimpleExpressionParser parser = ParserFactory.getInstance(args[0]);

        ParseTree tree = parser.statement();

        JFrame frame = new JFrame("Parser tree");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);

        viewer.setScale(scale);
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }

}
