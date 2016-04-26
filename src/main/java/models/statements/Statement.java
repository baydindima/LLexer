package models.statements;

public interface Statement {
    void printProgram(int offset, StringBuilder builder);
    void printTree(int offset);
    Statement tryResolve();
}
