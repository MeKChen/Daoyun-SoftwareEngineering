package com.gcsj.yunbanke.Service;

public interface ShortMessageService {
    Integer sendEmailMessage(String account ,String receiver, String purpose);

    Integer verifyEmailMessage(String code, String account, String email);
}