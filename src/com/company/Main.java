package com.company;

import com.ASTnode.*;
import com.lexer.Tokens;
import com.lexer.WordProcess;
import com.parser.SyntaxProcess;

public class Main {

    public static void main(String[] args) {
        Tokens MyTokens = new Tokens();
        String expr =  "(11+1))";

        WordProcess.getTokens(MyTokens, expr);
        Expr exp = SyntaxProcess.getAST(MyTokens);
        System.out.println(":"+exp);
    }

}
