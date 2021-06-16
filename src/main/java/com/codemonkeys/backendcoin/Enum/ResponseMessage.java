package com.codemonkeys.backendcoin.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseMessage {
    Success("200"),Forbidden("403"),
    Name_Repeat("用户名重复");

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseMessage(String s) {
        this.message=s;
    }


    @Override
    public String toString(){
        return this.message;
    }
}
