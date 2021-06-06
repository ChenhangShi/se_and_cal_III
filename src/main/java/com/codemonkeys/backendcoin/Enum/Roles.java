package com.codemonkeys.backendcoin.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Roles {
    ADMIN("ADMIN"),USER("USER");

    @EnumValue
    private final String role;
    Roles(String role) {
        this.role=role;
    }
    public String getRole(){
        return this.role;
    }
}
