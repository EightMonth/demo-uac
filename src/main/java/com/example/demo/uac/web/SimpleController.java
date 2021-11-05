package com.example.demo.uac.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/1 14:59
 */
@RestController
public class SimpleController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

}
