package com.yhhy.FFMail.setting.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMail.setting.domain.user.User;
import com.yhhy.FFMail.setting.service.user.UserEmailService;

/**
 * 和前端进行用户邮箱信息的交互
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/saveEmail")
public class UserEmailSaveController {
  @Autowired
  private UserEmailService  userEmailService;
  
  @ResponseBody
  @RequestMapping(value = "saveEmail", method = RequestMethod.GET)
  public JSONObject saveEmail(User u) {
    try {
      //Todo
    }catch(Exception e) {
      
    }
    return null;
    
  }
}
