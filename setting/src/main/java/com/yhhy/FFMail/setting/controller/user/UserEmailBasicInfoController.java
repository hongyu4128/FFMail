package com.yhhy.FFMail.setting.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMail.setting.common.bo.exceptions.emailinfo.EmailNotExistsException;
import com.yhhy.FFMail.setting.domain.user.UserEmailBasicInfo;
import com.yhhy.FFMail.setting.service.user.UserEmailBasicInfoService;
import com.yhhy.FFMailBasic.basic.common.JsonInterfaceTool;

/**
 * 和前端进行用户邮箱信息的交互
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/setting")
public class UserEmailBasicInfoController {
    private Logger log = LoggerFactory.getLogger(UserEmailBasicInfoController.class);

    @Autowired
    private UserEmailBasicInfoService userEmailBasicInfoService;

    @ResponseBody
    @RequestMapping(value = "saveUserEmail", method = RequestMethod.GET)
    public JSONObject saveUserEmail(@RequestBody UserEmailBasicInfo email) {
        try {
            userEmailBasicInfoService.saveEmailInfo(email);
            return JsonInterfaceTool.succeed("邮箱保存成功");
        } catch (EmailNotExistsException e) {
            log.error(e.getMsg());
            return JsonInterfaceTool.fail(e.getMsg());
        } catch (Exception e) {
            log.error(e.getMessage());
            return JsonInterfaceTool.fail("抱歉,邮箱保存失败了");
        }

    }
}
