package com.lexer;




import java.util.Vector;

public class Tokens implements Cloneable{
    public Vector<Pair> word;
    private int index=0;

    public Tokens(){
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

