package com.lexer;


public class Pair {

    private String str;
    private TokenType id;

    public TokenType getId() {
        return id;
    }


    public String getStr() {
        return str;
    }

    public Pair(String _str, TokenType _id) {
        str = _str;
        id = _id;

    }

    @Override
    public String toString() {

        return "Token: " + "<" + str + "," + id + ">";
    }

    @Override
    public Object clone() {
        var newObj = new Pair();
        newObj.id = this.id;
        newObj.str = this.str;
        return newObj;
    }

    Pair() {
        str = null;
        id = TokenType.EMPTY;

    }


}
