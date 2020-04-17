package com.gcsj.yunbanke.Service.Impl;

import com.gcsj.yunbanke.Entity.ShortMessage;
import com.gcsj.yunbanke.Repository.ShortMessageRepository;
import com.gcsj.yunbanke.Service.ShortMessageService;
import com.gcsj.yunbanke.Utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("shortMessageService")
public class ShortMessageServiceImpl implements ShortMessageService {
    @Autowired
    ShortMessageRepository shortMessageRepository;

    /**
     * 发送邮箱验证码
     * @param account
     * @param receiver
     * @param purpose
     * @return
     */
    @Override
    public Integer sendEmailMessage(String account, String receiver, String purpose) {
        if ("".equals(receiver)) {
            return 1; //接收人不能为空
        }
        String code = EmailUtils.editEmail(receiver);
        if ("".equals(code)) {
            return 2; //短信功能异常
        }
        shortMessageRepository.save(new ShortMessage(account, receiver, code, new Date(), purpose));
        return 0;
    }

    /**
     * 验证邮箱验证码
     * @param code
     * @param account
     * @param email
     * @return
     */
    @Override
    public Integer verifyEmailMessage(String code, String account, String email) {
        ShortMessage msgList = shortMessageRepository.findShortMessagesByAccountEmail(account, email);
        if (msgList.getCode().equals(code)) {
            return 0;
        }
        return 1; //验证错误
    }
}