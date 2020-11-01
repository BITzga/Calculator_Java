package com.company;

import com.ASTnode.*;
import com.lexer.Tokens;
import com.lexer.WordProcess;
import com.parser.SyntaxProcess;

public class Main {

    public static void main(String[] args) {
        Tokens MyTokens = new Tokens();
        String expr =  "(11+ 1）";
        Expr exp = new Expr();

        boolean check = WordProcess.getTokens(MyTokens, expr);
        if (check){
            exp = SyntaxProcess.getAST(MyTokens);
            System.out.println(":"+exp);
        }
        else System.out.println(":"+exp);
    }

}
