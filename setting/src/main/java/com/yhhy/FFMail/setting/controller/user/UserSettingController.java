package com.yhhy.FFMail.setting.controller.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMail.setting.common.bo.exceptions.user.UserNotExistsException;
import com.yhhy.FFMail.setting.common.bo.exceptions.user.UserWrongPasswordException;
import com.yhhy.FFMail.setting.common.util.JsonInterfaceTool;
import com.yhhy.FFMail.setting.domain.user.User;
import com.yhhy.FFMail.setting.service.user.UserSettingService;

@Controller
@RequestMapping("/setting")
@Validated
public class UserSettingController {
  private Logger log = LoggerFactory.getLogger(UserSettingController.class);

  @Autowired
  private UserSettingService userSettingService;

  // localhost:9092/setting/saveUser?user=ABC&password=BCD
  @ResponseBody
  @RequestMapping(value = "saveUser", method = RequestMethod.GET)
  public JSONObject saveUser(@RequestParam String userName, @RequestParam String telephone,
      @RequestParam String password) {
    try {
      @Valid
      User user = new User(userName, telephone, password);
      userSettingService.saveUser(user);
      return JsonInterfaceTool.succeed("恭喜您注册成功~~~");
    } catch (UserNotExistsException e) {
      e.printStackTrace();
      log.error(e.getMsg());
      return JsonInterfaceTool.fail(e.getMsg());
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage());
      return JsonInterfaceTool.fail("注册失败啦,我们正在处理哦~~~~~~");
    }
  }

  // 用于登录
  @ResponseBody
  @RequestMapping(value = "userLogin", method = RequestMethod.GET)
  public JSONObject userLogin(String userName, String password) {
    try {
      User user = new User(userName, userName, password);
      return JsonInterfaceTool.succeed(userSettingService.login(user));
//      Boolean userIsExist = userSettingService.userIsExist(user);
//      if (userIsExist == true) {
//        return JsonInterfaceTool.succeed("恭喜您登陆成功");
//      } else {
//        return JsonInterfaceTool.fail("用户不存在请先注册");
//      }
    } catch (UserNotExistsException | UserWrongPasswordException e) {
      e.printStackTrace();
      log.error(e.getMessage());
      return JsonInterfaceTool.fail(e.getMsg());
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage());
      return JsonInterfaceTool.fail("登陆失败,我们正在处理哦");
    }
  }

}
