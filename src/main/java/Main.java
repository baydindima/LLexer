import javafx.scene.control.TreeView;
import org.antlr.v4.runtime.tree.gui.TreeViewer;

/**
 * Created by John on 4/9/2016.
 */
public class Main {
    public static void main(String[] args) {
        TreeViewer treeViewer;
        TreeView<Integer> treeView;
        System.out.println(Calculator.CALCULATOR.parse("1 + 2 * 3 + 25 / 5"));
    }
}
