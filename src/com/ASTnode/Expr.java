package com.ASTnode;

public class Expr {

    protected Number mynum;
    public Expr(Number mynum) {
        this.mynum = mynum;
    }
    public Expr(){



    }

    public Number getMynum() {
        return mynum;
    }

    @Override
    public String toString() {
        return "EMPTY AST!";
    }

    public ExprType getEType() {
         return ExprType.EMPTY;
     }

}

