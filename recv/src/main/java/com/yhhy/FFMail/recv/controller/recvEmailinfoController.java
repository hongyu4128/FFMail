package com.yhhy.FFMail.recv.controller;

import javax.mail.AuthenticationFailedException;
import javax.mail.NoSuchProviderException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMail.recv.service.RecvEmailService;
import com.yhhy.FFMailBasic.basic.common.JsonInterfaceTool;
import com.yhhy.FFMailBasic.basic.domain.user.email.UserEmailBasicInfo;

/**
 * 进行邮箱收取的服务
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/recv")
public class recvEmailinfoController {
    private Logger log = LoggerFactory.getLogger(recvEmailinfoController.class);

    @Autowired
    @Qualifier("POP3RecvEmailService")
    private RecvEmailService POP3RecvEmailService;

    @Autowired
    @Qualifier("IMAPRecvEmailService")
    private RecvEmailService IMAPRecvEmailService;

    @ResponseBody
    @RequestMapping(value = "recvEmailInfo", method = RequestMethod.POST)
    public JSONObject recvEmailInfo(@RequestBody UserEmailBasicInfo emailInfo) {
        try {
            if (emailInfo.getRecvServiceType().equals("pop3")) {
                POP3RecvEmailService.recvEmail(emailInfo);
            } else if (emailInfo.getRecvServiceType().equals("imap")) {
                IMAPRecvEmailService.recvEmail(emailInfo);
            }
            return JsonInterfaceTool.succeed("收取邮件成功");
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
            return JsonInterfaceTool.fail("用户名或者密码错误");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return JsonInterfaceTool.fail("NoSuchProviderException");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonInterfaceTool.fail(e.getLocalizedMessage());
        }

    }

}
