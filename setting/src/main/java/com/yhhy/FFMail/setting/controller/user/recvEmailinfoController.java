package com.yhhy.FFMail.setting.controller.user;

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
  @Autowired
  private RecvEmailService recvEmailService;
  
  @ResponseBody
  @RequestMapping(value = "recvEmailInfo", method = RequestMethod.POST )
  public JSONObject recvEmailInfo(@RequestBody UserEmailBasicInfo emailInfo) {
    try {
      recvEmailService.recvEmail(emailInfo);
      return JsonInterfaceTool.succeed("收取简历成功");
    }catch(Exception e) {
      return JsonInterfaceTool.fail("收取简历异常");
    }
    
  }

}
