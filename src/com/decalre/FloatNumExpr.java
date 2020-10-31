package com.decalre;

import java.beans.Expression;

public class FloatNumExpr extends Expr {

    private ExprType EType;
    private Float Fnum;

    public FloatNumExpr(Float Fnum) {
        EType = ExprType.FLOAT;
        this.Fnum = Fnum;
    }
    public FloatNumExpr(){
        EType = ExprType.EMPTY;

    }

    public void setEType(ExprType EType) {
        this.EType = EType;
    }

    public void setFnum(Float fnum) {
        Fnum = fnum;
    }
    public void setSign(int sign){

        Fnum = sign*Fnum;
    }

    @Override
    public String toString() {
        return " " + Fnum;
    }

    @Override
    public ExprType getEType() {
        return EType;
    }

    public Float getFnum() {
        return Fnum;
    }
}
