package com.passwordsafe;;

public class PasswordInfo {
    private String plain;
    private String name;

    public PasswordInfo(String plain, String name) {
        this.plain = plain;
        this.name = name;
    }

    public String getName()  {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPlain() {
        return plain;
    }
    public void setPlain(String plain) {
        this.plain = plain;
    }
}
