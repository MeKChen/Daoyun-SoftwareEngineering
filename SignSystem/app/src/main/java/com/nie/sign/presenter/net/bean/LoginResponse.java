package com.nie.sign.presenter.net.bean;

public class LoginResponse {


    /**
     * userName : null
     * userId : 25
     * account : 1171636667
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJEQVRBX0VYVCI6MTU5MzMzOTQxOTIzMywiREFUQV9VU0VSUk9MRUlEIjoyNiwiREFUQV9VU0VSSUQiOjI1fQ.CS-zVTkRRCR9o5nTD4zcYGsTPtnGdVEspaFx08fk-Uw
     */

    private Object userName;
    private int userId;
    private String account;
    private String token;

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
