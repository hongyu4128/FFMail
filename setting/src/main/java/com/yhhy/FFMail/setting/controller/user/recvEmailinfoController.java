package com.yhhy.FFMail.setting.controller.user;


import java.io.IOException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMail.setting.domain.user.UserEmailBasicInfo;
import com.yhhy.FFMail.setting.service.user.RecvEmailService;
import com.yhhy.FFMailBasic.basic.common.JsonInterfaceTool;

/**
 * 进行邮箱收取的服务
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/setting")
public class recvEmailinfoController {
    private Logger log = LoggerFactory.getLogger(UserEmailBasicInfoController.class);

  @Autowired
  private RecvEmailService recvEmailService;
  @ResponseBody
  @RequestMapping(value = "recvEmailInfo", method = RequestMethod.POST )
  public JSONObject recvEmailInfo(@RequestBody UserEmailBasicInfo emailInfo) {
    try {
      recvEmailService.recvEmail(emailInfo);
      return JsonInterfaceTool.succeed("收取简历成功");
    }catch(AuthenticationFailedException e) {
      return JsonInterfaceTool.fail("用户名或密码错误");
    }catch(NoSuchProviderException e) {
    	log.error(e.getLocalizedMessage());
    	return JsonInterfaceTool.fail(e.getLocalizedMessage());
    }
    catch(Exception e) {
    	log.error(null, e.getStackTrace());
        return JsonInterfaceTool.fail(e.getStackTrace());
    }
    
  }

}
