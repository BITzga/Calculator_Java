package com.company;

import java.util.Vector;

public class Tokens implements Cloneable{
    Vector<Pair> word;
    private int index=0;

    Tokens(){
        word = new Vector<Pair>();
        index = 0;
    }
    @Override
    public Object clone(){

        var newObj = new Tokens();
        newObj.setIndex(this.getIndex());
        newObj.word = (Vector<Pair>) this.word.clone();
        //word.add(new Pair(" ",TokenType.EMPTY));
        return newObj;
    }
    public Tokens(Vector<Pair> word) {
        this.word = word;
    }
    public void PlusIndex(){
        if(this.index<this.word.size()-1)
            this.index++;
   }

    public Tokens(Vector<Pair> word, int index) {
        this.word = word;
        this.index = index;
    }

    @Override
    public String toString() {

        StringBuilder TokensContent  = new StringBuilder();
        for (Pair w : word) {
            TokensContent.append("\tToken:"+"<"+w.getStr()+"\t,"+w.getId()+">"+"\n");

        }
        return "Tokens{\n" +
                TokensContent + "}";
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Vector<Pair> getWord() {
        return word;
    }
}
class Pair {

    private String str;
    private TokenType id;

    public TokenType getId() {
        return id;
    }


    public String getStr() {
        return str;
    }

    Pair(String _str, TokenType _id) {
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
