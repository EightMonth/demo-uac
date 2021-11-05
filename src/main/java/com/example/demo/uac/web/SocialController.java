package com.example.demo.uac.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/3 10:15
 */
@Controller
@RequestMapping("social")
public class SocialController {

    @GetMapping("qq")
    public String qq() {
        return "qq";
    }
}
