package com.company;

import com.decalre.*;

public class Main {

    public static void main(String[] args) {
        Tokens MyTokens = new Tokens();
        String expr = "1 ";

        WordProcess.getTokens(MyTokens, expr);

        try {
            Expr exp = getExpr(MyTokens);
            System.out.println(MyTokens.getIndex());
            System.out.println(MyTokens.word.size() - 1);
            if (MyTokens.getIndex() + 1 <= MyTokens.word.size() - 1) {

                throw new SyntaxException("发现多余字符", MyTokens.getIndex() + 1, MyTokens.word.size() - 1);

            } else {
                System.out.println("语法正确！");
                System.out.println(exp.toString());

            }
        } catch (SyntaxException e) {

            System.out.println(e);
        }


    }


    public static void trimSpace(Tokens MyTokens) {
        if (MyTokens.getIndex() > MyTokens.word.size() - 1) {
            return;
        }

        Tokens MyTokensBuff;
        MyTokensBuff = (Tokens) MyTokens.clone();

        Pair p = MyTokensBuff.word.get(MyTokensBuff.getIndex());

        while (p.getId().equals(TokenType.SPACE)) {
            MyTokensBuff.PlusIndex();
            p = MyTokensBuff.word.get(MyTokensBuff.getIndex());
        }
        MyTokens.setIndex(MyTokensBuff.getIndex());
    }

    public static boolean Is(Tokens MyTokens, TokenType Text) {
        if (MyTokens.getIndex() > MyTokens.word.size() - 1) {
            return false;
        }

        trimSpace(MyTokens);
        Tokens MyTokensBuff;
        MyTokensBuff = (Tokens) MyTokens.clone();


        Pair p = MyTokensBuff.word.get(MyTokensBuff.getIndex());

        if (p.getId().equals(Text)) {
            MyTokens.PlusIndex();
            return true;
        } else {
            return false;
        }

    }

    public static Expr getNumber(Tokens MyTokens, int sign) {
        boolean gotFlout = false;
        boolean gotInt = false;
        trimSpace(MyTokens);
        Tokens MyTokensBuff;
        MyTokensBuff = (Tokens) MyTokens.clone();

        FloatNumExpr FRes = new FloatNumExpr();
        IntNumExpr IRes = new IntNumExpr();
        Pair p = MyTokensBuff.word.get(MyTokensBuff.getIndex());

        while (true) {

            if (p.getId().equals(TokenType.FLOAT)) {

                Float num = Float.valueOf(MyTokensBuff.word.get(MyTokensBuff.getIndex()).getStr());
                FRes.setFnum((float) sign * num);
                FRes.setEType(ExprType.FLOAT);
                gotFlout = true;

                MyTokensBuff.PlusIndex();


            } else if (p.getId().equals(TokenType.INT)) {
                Integer num = Integer.valueOf(MyTokensBuff.word.get(MyTokensBuff.getIndex()).getStr());
                IRes.setInum((int) sign * num);
                IRes.setEType(ExprType.INT);
                gotInt = true;

                MyTokensBuff.PlusIndex();

            } else {
                break;
            }
            if (MyTokensBuff.getIndex() == MyTokens.word.size()) {
                // MyTokensBuff.setIndex(MyTokens.word.size()-1);
                break;
            }
            p = MyTokensBuff.word.get(MyTokensBuff.getIndex());
        }


        if (gotFlout) {
            MyTokens.setIndex(MyTokensBuff.getIndex());
            return FRes;

        } else if (gotInt) {
            MyTokens.setIndex(MyTokensBuff.getIndex());

            return IRes;

        } else {
            throw new SyntaxException("此处需要表达式", MyTokensBuff.getIndex() + 1, MyTokensBuff.word.size());
        }


    }

    public static Expr getTerm(Tokens MyTokens) {
        int sign = 1;
        try {

            if (Is(MyTokens, TokenType.MINUS)) {
//check
                sign = -1;
            } else if (Is(MyTokens, TokenType.PLUS)) {
//check
                sign = 1;
            }
            return getNumber(MyTokens, sign);

        } catch (RuntimeException e) {
            Tokens MyTokensBuff;
            MyTokensBuff = (Tokens) MyTokens.clone();

            if (Is(MyTokensBuff, TokenType.LEFT)) {

                Expr Result = getExpr(MyTokensBuff);
                if ((Result.getEType() == ExprType.FLOAT)) {
                    ((FloatNumExpr) Result).setSign(sign);
                } else if ((Result.getEType() == ExprType.INT)) {
                    ((IntNumExpr) Result).setSign(sign);
                }

                if (Is(MyTokensBuff, TokenType.RIGHT)) {

                    MyTokens.setIndex(MyTokensBuff.getIndex());
                    return Result;

                } else
                    throw new SyntaxException("此处需要右括号", MyTokensBuff.getIndex(), MyTokensBuff.word.size() - 1);

            } else {
                throw e;
            }

        }

    }

    public static Expr getFactor(Tokens MyTokens) {
        Tokens MyTokensBuff;
        MyTokensBuff = (Tokens) MyTokens.clone();
        Expr Result = getTerm(MyTokensBuff);
        boolean checkFactor = false;

        while (true) {
            var Operator = ExprType.EMPTY;

            if (Is(MyTokensBuff, TokenType.MULTI)) {
                Operator = ExprType.MULTI;
            } else if (Is(MyTokensBuff, TokenType.DIVIDE)) {
                Operator = ExprType.DIVIDE;
            } else break;

            if (Operator.compareTo(ExprType.EMPTY) != 0) {
                checkFactor = true;
                try {
                    Result = new BinaryExpr(Operator, Result, getFactor(MyTokensBuff));
                } catch (RuntimeException e) {
                    throw e;
                }

            }
        }
        MyTokens.setIndex(MyTokensBuff.getIndex());
        return Result;


    }

    public static Expr getExpr(Tokens MyTokens) {
        Tokens MyTokensBuff;
        MyTokensBuff = (Tokens) MyTokens.clone();
        Expr Result = getFactor(MyTokensBuff);
        boolean checkE = false;
        while (true) {
            ExprType Operator;
            if (Is(MyTokensBuff, TokenType.PLUS))
                Operator = ExprType.PLUS;
            else if (Is(MyTokensBuff, TokenType.MINUS))
                Operator = ExprType.MINUS;
            else {
                //System.out.println("此处需要+ 或 - position:"+MyTokensBuff.getIndex());
                break;
            }

            if (Operator.compareTo(ExprType.EMPTY) != 0) {
                checkE = true;
                try {
                    Result = new BinaryExpr(Operator, Result, getFactor(MyTokensBuff));
                } catch (RuntimeException e) {
                    throw e;
                }

            }
        }
        MyTokens.setIndex(MyTokensBuff.getIndex());
        return Result;
    }
//词法分析

//词法分析

}


