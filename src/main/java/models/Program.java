package models;

import models.statements.Statement;

public class Program {
    private Statement statement;

    public Program(Statement statement) {
        this.statement = statement;
    }

    public void printProgram() {
        StringBuilder builder = new StringBuilder();
        statement.printProgram(1, builder);
        builder.append("\n");
        System.out.println(builder.toString());
    }

    public void printTree() {
        statement.printTree(1);
    }

    public void tryResolve() {
        statement = statement.tryResolve();
    }

}
