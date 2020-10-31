package com.parser;

public class SyntaxException extends RuntimeException{
    private int sPos;
    private int ePos;

    public SyntaxException(String message,int sPos,int ePos) {
        super(message);
        this.sPos = sPos;
        this.ePos = ePos;
    }
    public SyntaxException(String message,int sPos) {
        super(message);
        this.sPos = sPos;
        this.ePos = sPos;
    }
    public int getsPos() {
        return sPos;
    }

    @Override
    public String toString() {
        if (sPos == ePos)
            return super.toString() + " position: " + sPos;
        else
            return super.toString()  + " position: from "+ sPos + " to: "+ ePos;
    }
}
