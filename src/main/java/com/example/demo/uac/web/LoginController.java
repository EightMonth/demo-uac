package com.example.demo.uac.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/5 10:34
 */
@Controller
public class LoginController {
    @GetMapping("ipLogin")
    public String ipLogin() {
        return "ipLogin";
    }

    @GetMapping("logoutSuccess")
    public String logout() {
        return "logoutSuccess";
    }

}
