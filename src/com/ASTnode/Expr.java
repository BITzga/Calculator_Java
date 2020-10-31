package com.ASTnode;

public class Expr {
    public Expr() {
    }

    @Override
    public String toString() {
        return "EMPTY AST!";
    }

    public ExprType getEType() {
         return ExprType.EMPTY;
     }

}

