package com.company;

import com.ASTnode.*;
import com.calculate.CalAST;
import com.lexer.Tokens;
import com.lexer.WordProcess;
import com.parser.SyntaxProcess;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        while (true){

            Tokens MyTokens = new Tokens();
            String expr = JOptionPane.showInputDialog("请输入四则运算表达式");
            if(expr==null)
                break;
            // String expr =  "(11+ 1+0.0)";
            Expr exp = new Expr();

            boolean check = WordProcess.getTokens(MyTokens, expr);
            if (check){
                exp = SyntaxProcess.getAST(MyTokens);
                System.out.println("语法树: "+exp);
                //System.out.println(exp.getClass().toString());
                if(exp.toString()!="EMPTY AST!"&&exp instanceof BinaryExpr)
                    System.out.println("result: "+CalAST.inorderTravelAST((BinaryExpr) exp));
                else {
                    System.out.println("result: "+exp);

                }
            }
            else System.out.println("语法树: "+exp);
            System.out.println("*****************");
        }

    }

}
