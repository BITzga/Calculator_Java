package com.company;

import java.util.Vector;

public class WordProcess {

    public static void getTokens(Tokens MyTokens, String expr) {
        boolean err_check = wordAnalyze(MyTokens.word, expr);
        MyTokens.word.add(new Pair(" ",TokenType.EMPTY));

        System.out.println(MyTokens);

        if (err_check == true) {
            System.out.println("词法正确!");
        }

    }

    public static boolean isCharater(char c) {
        return c == '(' || c == ')' || c == '+'
                || c == '-' || c == '*' || c == '/' || c == ' ';

    }

    public static boolean isDigit(char c) {

        return c >= '0' && c <= '9';

    }

    public static boolean wordAnalyze(Vector<Pair> word, String expr) {

        char[] exp = new char[expr.length() + 1];
        for (int i = 0; i < expr.length(); i++)
            exp[i] = expr.charAt(i);
        System.out.println(exp);
        for (int i = 0; i < expr.length(); i++) {
            boolean checkFloat = false;
            // 如果是 + - * / () 存起来 方便后续 中缀转后缀
            if (isCharater(exp[i])) {
                StringBuilder tmp = new StringBuilder();
                tmp.append(exp[i]);
                switch (exp[i]) {
                    case ' ':
                        word.add(new Pair(tmp.toString(), TokenType.SPACE));
                        break;
                    case '+':
                        word.add(new Pair(tmp.toString(), TokenType.PLUS));
                        break;
                    case '-':
                        word.add(new Pair(tmp.toString(), TokenType.MINUS));
                        break;
                    case '*':
                        word.add(new Pair(tmp.toString(), TokenType.MULTI));
                        break;
                    case '/':
                        word.add(new Pair(tmp.toString(), TokenType.DIVIDE));
                        break;
                    case '(':
                        word.add(new Pair(tmp.toString(), TokenType.LEFT));
                        break;
                    case ')':
                        word.add(new Pair(tmp.toString(), TokenType.RIGHT));
                        break;

                }
            }
            //存数字
            else if (isDigit(exp[i])) {

                StringBuilder tmp = new StringBuilder();

                while (isDigit(exp[i])) {
                    tmp.append(exp[i]);
                    ++i;
                }
                if (exp[i] == '.') {

                    ++i;
                    if (isDigit(exp[i])) {
                        checkFloat = true;
                        tmp.append('.');
                        while (isDigit(exp[i])) {

                            tmp.append(exp[i]);
                            ++i;
                        }
                    } else {
                        TokenException e = new TokenException("小数尾数不完整！",i);
                        System.out.println(e);
                        return false;
                    }
                }
                if (checkFloat)
                    word.add(new Pair(tmp.toString(), TokenType.FLOAT));
                else {
                    word.add(new Pair(tmp.toString(), TokenType.INT));

                }

                --i;

            }
            //以‘.’开头
            else {
                TokenException e =  new TokenException("小数前缀不完整！",i);
                System.out.println(e);
                return false;
            }

        }
        return true;
    }
}
