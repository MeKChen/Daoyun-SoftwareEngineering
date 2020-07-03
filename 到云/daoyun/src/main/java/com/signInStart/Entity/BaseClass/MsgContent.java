package com.signInStart.Entity.BaseClass;

import java.io.Serializable;

public class MsgContent implements Serializable {
    public enum TYPE{
        EMAIL,
        LOGIN,
        FORGETPWD,
    }

    public String revicer;
    public String type;

    public MsgContent() {
    }

    public MsgContent(String revicer, String type) {
        this.revicer = revicer;
        this.type = type;
    }

    @Override
    public String toString() {
        return revicer+"_"+type;
    }
}