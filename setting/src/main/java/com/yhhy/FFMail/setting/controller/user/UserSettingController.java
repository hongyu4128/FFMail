package com.yhhy.FFMail.setting.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMail.setting.common.util.JsonInterfaceTool;
import com.yhhy.FFMail.setting.domain.user.User;
import com.yhhy.FFMail.setting.service.user.UserSettingService;

@Controller
@RequestMapping("/setting")
public class UserSettingController {

  @Autowired
  private UserSettingService userSettingService;

  // localhost:9092/setting/saveUser?user=ABC&password=BCD
  @ResponseBody
  @RequestMapping(value = "saveUser", method = RequestMethod.GET)
  public JSONObject saveUser(@RequestParam String userName, @RequestParam String password) {
    try {
      User user = new User(userName, password);
      userSettingService.saveUser(user);
      return JsonInterfaceTool.succeed("保存成功~~~");
    } catch (Exception e) {
      return JsonInterfaceTool.fail("错误异常~~~~~~");
    }
  }
}
