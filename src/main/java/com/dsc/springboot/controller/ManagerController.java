package com.dsc.springboot.controller;

import javax.servlet.http.HttpSession;

import com.dsc.springboot.config.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dsc.springboot.model.Manager;
import com.dsc.springboot.service.ManagerService;

@RestController("")
public class ManagerController {

    @Autowired
    private ManagerService loginService;

    String sessionKey = MyIntercepter.SESSION_KEY;

    @PostMapping("/manager/login")
    public String login(@RequestBody Manager manager, HttpSession session) {
        try {
            Manager curManager = loginService.login(manager.getLoginName(), manager.getPassword());
            if (curManager != null) {
                session.setMaxInactiveInterval(6000);
                session.setAttribute(sessionKey, curManager);
                return "{\"result\":\"true\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":\"false\"}";
    }

    @PostMapping("/manager/exit")
    public String exit(HttpSession session) {
        session.removeAttribute(sessionKey);
        return "{\"result\":\"true\"}";
    }

    @RequestMapping("/manager/getCurManager")
    public Manager getCurManager(HttpSession session) {
        return (Manager) session.getAttribute(sessionKey);
    }

    /**
     * 用于安卓端登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/test/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (username.equals("dsc") && password.equals("1")) {
            return "{\"result\":\"true\"}";
        }
        return "{\"result\":\"false\"}";

    }
}
