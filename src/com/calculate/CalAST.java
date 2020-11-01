package com.calculate;

import com.ASTnode.*;

public class CalAST {  //中序遍历计算语法树  得出结果
    public static Expr inorderTravelAST(BinaryExpr ASTnode) {

        if (ASTnode instanceof BinaryExpr) {
            if ( ASTnode.left instanceof BinaryExpr) {

                 ASTnode.left = inorderTravelAST((BinaryExpr)ASTnode.left);

            }
            if ( ASTnode.right instanceof BinaryExpr) {

                ASTnode.right = inorderTravelAST((BinaryExpr)ASTnode.right);
            }

        }

        return calNode(ASTnode);
    }


    /*

              +
           +    8
    *    1  2
    *
    *
    *
    *
    *
    * */
    private static Expr calNode(BinaryExpr node){

        float fleft = 0;
        float fright = 0;
        int ileft = 0;
        int iright = 0;

        if(node.left instanceof BinaryExpr){
            fleft =  ((BinaryExpr)node.left).getResNum();
        }
        else {
            if(node.left.getEType().equals(ExprType.FLOAT))
                fleft = ((FloatNumExpr)node.left).getFnum();
            else ileft = ((IntNumExpr)node.left).getInum();
        }
        if(node.right instanceof BinaryExpr){
            fright = ((BinaryExpr)node.right).getResNum();
        }
        else {
            if(node.right.getEType().equals(ExprType.FLOAT))
                fright = ((FloatNumExpr)node.right).getFnum();
            else iright = ((IntNumExpr)node.right).getInum();

        }

        switch (node.getEType()){
            case PLUS -> {
                //Float res  = 9.9;
                node.setEType(ExprType.FLOAT);
                node.setResNum(fleft+fright+ileft+iright);
                node.left = null;
                node.right = null;
                return node;
            }
            case MINUS -> {
                //Float res  = (Float)leftNode.getMynum() - (Float)rightNode.getMynum();
                node.setEType(ExprType.FLOAT);
                node.setResNum(fleft-fright+ileft-iright);
                node.left = null;
                node.right = null;
                return node;
            }
            case MULTI ->{
                //Float res  = (Float)leftNode.getMynum() * (Float)rightNode.getMynum();
                node.setEType(ExprType.FLOAT);
                node.setResNum((fleft+ileft)*(fright+iright));
                node.left = null;
                node.right = null;
                return node;
            }
            case DIVIDE -> {
               // Float res  = (Float)leftNode.getMynum() / (Float)rightNode.getMynum();
                node.setEType(ExprType.FLOAT);
                node.setResNum((fleft+ileft)/(fright+iright));
                node.left = null;
                node.right = null;
                return node;
            }

        }
    return null;
    }


}
