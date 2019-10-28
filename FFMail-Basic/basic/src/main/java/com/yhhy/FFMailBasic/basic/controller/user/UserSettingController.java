package com.yhhy.FFMailBasic.basic.controller.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMailBasic.basic.bo.exceptions.user.UserNotExistsException;
import com.yhhy.FFMailBasic.basic.bo.exceptions.user.UserWrongPasswordException;
import com.yhhy.FFMailBasic.basic.common.JsonInterfaceTool;
import com.yhhy.FFMailBasic.basic.domain.user.User;
import com.yhhy.FFMailBasic.basic.service.user.UserSettingService;

@Controller
@RequestMapping("/user")
@Validated
public class UserSettingController {
    private Logger log = LoggerFactory.getLogger(UserSettingController.class);

    @Autowired
    private UserSettingService userSettingService;

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public JSONObject saveUser(@RequestBody @Valid User user) {
        try {
            userSettingService.register(user);
            return JsonInterfaceTool.succeed("恭喜您注册成功~~~");
        } catch (UserNotExistsException e) {
            log.error(e.getMsg());
            return JsonInterfaceTool.fail(e.getMsg());
        } catch (Exception e) {
            log.error(e.getMessage());
            return JsonInterfaceTool.fail("注册失败啦,我们正在处理哦~~~~~~");
        }
    }

    // 用于登录
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public JSONObject userLogin(String userName, String password) {
        try {
            User user = new User(userName, userName, password);
            return JsonInterfaceTool.succeed(userSettingService.login(user));
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
