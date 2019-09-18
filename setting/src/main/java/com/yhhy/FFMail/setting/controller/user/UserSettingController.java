package com.yhhy.FFMail.setting.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhhy.FFMail.setting.service.user.UserSettingService;

@Controller
@RequestMapping("/setting")
public class UserSettingController {
    @Autowired
    private UserSettingService userSettingService;

}
