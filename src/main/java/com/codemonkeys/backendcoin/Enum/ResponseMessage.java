package com.codemonkeys.backendcoin.Enum;

public enum ResponseMessage {
    Success("200"),Forbidden("403");

    private String message;
    ResponseMessage(String s) {
        this.message=s;
    }

    @Override
    public String toString(){
        return this.message;
    }
}
