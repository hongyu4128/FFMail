package com.yhyh.FFMail.send.controller;

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
import com.yhhy.FFMailBasic.basic.common.JsonInterfaceTool;
import com.yhyh.FFMail.send.domain.SendEmail;
import com.yhyh.FFMail.send.service.SendEmailService;

/**
 * 进行邮箱收取的服务
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/send")
public class SendEmailinfoController {
    private Logger log = LoggerFactory.getLogger(SendEmailinfoController.class);

    @Autowired
    @Qualifier("sendEmailService")
    private SendEmailService sendEmailService;


    @ResponseBody
    @RequestMapping(value = "sendEmailInfo", method = RequestMethod.POST)
    public JSONObject recvEmailInfo(@RequestBody SendEmail sendInfo) {
        try {
                sendEmailService.sendEmail(sendInfo);
            return JsonInterfaceTool.succeed("发送邮件成功");
//        } catch (AuthenticationFailedException e) {
//            e.printStackTrace();
//            return JsonInterfaceTool.fail("用户名或者密码错误");
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//            return JsonInterfaceTool.fail("NoSuchProviderException");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonInterfaceTool.fail(e.getLocalizedMessage());
        }

    }

}
