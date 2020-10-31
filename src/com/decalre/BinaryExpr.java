package com.decalre;



public class BinaryExpr extends Expr {

    private ExprType EType;
    Expr left,right;

    public BinaryExpr(){
        this.EType = ExprType.EMPTY;
    }
    public BinaryExpr(ExprType EType,Expr left,Expr right){
       this.left = left;
       this.right = right;
        this.EType = EType;
    }

    public BinaryExpr(ExprType EType) {
        try{
            if(EType.compareTo(ExprType.INT)<0)
                this.EType = EType;
        else
          throw new Exception();
        }catch (Exception e){

            System.out.println("不是运算符！");
        }

    }

    @Override
    public ExprType getEType() {
      return EType;
    }
    @Override
    public String toString(){

      return "("+EType.toString()+left.toString()+" "+right.toString()+")";

    }

}
