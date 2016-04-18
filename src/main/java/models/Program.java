package models;

import models.statements.Statement;

public class Program {
    private Statement statement;

    public Program(Statement statement) {
        this.statement = statement;
    }

    public void printProgram() {
        statement.printProgram(1);
    }

    public void printTree() {
        statement.printTree(1);
    }

    public void tryResolve() {
        statement = statement.tryResolve();
    }

}
