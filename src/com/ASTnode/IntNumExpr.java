package com.ASTnode;

public class IntNumExpr extends Expr {

    private ExprType EType;
    private Integer Inum;


    @Override
    public String toString() {
        return  " "+Inum
                ;
    }
    public void setSign(int sign){

        Inum = sign*Inum;
    }
    public IntNumExpr(Integer Inum) {
        EType = ExprType.INT;
        this.Inum = Inum;
    }
    public IntNumExpr(){
        EType = ExprType.EMPTY;
    }

    public void setEType(ExprType EType) {
        this.EType = EType;
    }

    public void setInum(Integer inum) {
        Inum = inum;
    }

    public Integer getInum() {
        return Inum;
    }

    @Override
    public ExprType getEType() {
        return EType;
    }
}
