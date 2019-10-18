package com.yhhy.FFMail.send.controller;

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
import com.yhhy.FFMail.send.domain.SendEmailinfo;
import com.yhhy.FFMail.send.service.SendEmailService;
import com.yhhy.FFMailBasic.basic.common.JsonInterfaceTool;

/**
 * 进行邮箱发送的服务
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/send")
public class SendEmailinfoController {
    private Logger log = LoggerFactory.getLogger(SendEmailinfoController.class);

    @Autowired
    private SendEmailService sendEmailService;


    @ResponseBody
    @RequestMapping(value = "sendEmailInfo", method = RequestMethod.POST)
    public JSONObject recvEmailInfo(@RequestBody SendEmailinfo sendInfo) {
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
