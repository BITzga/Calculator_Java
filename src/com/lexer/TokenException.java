package com.lexer;

public class TokenException extends RuntimeException {
    private int sPos;
    private int ePos;

    public TokenException(String message, int sPos, int ePos) {
        super(message);
        this.sPos = sPos;
        this.ePos = ePos;
    }
    public TokenException(String message, int sPos) {
        super(message);
        this.sPos = sPos;
        this.ePos = sPos;
    }

    @Override
    public String toString() {
        if (sPos == ePos)
            return super.toString() + " position: " + sPos;
        else
            return super.toString() + " position: from " + sPos + " to: " + ePos;
    }

    public int getsPos() {
        return sPos;
    }
}
