package com.dsc.springboot.controller;

import com.dsc.springboot.config.MyIntercepter;
import com.dsc.springboot.dao.ResponseDao;
import com.dsc.springboot.model.Manager;
import com.dsc.springboot.service.ManagerService;
import com.dsc.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;

    String sessionKey = MyIntercepter.SESSION_KEY;

    @RequestMapping("/login")
    public String login(HttpSession session) {
        return "login";
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        session.removeAttribute(sessionKey);
        return "redirect:login";
    }

    @RequestMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute(sessionKey) != null) {
            return "home";
        }
        return "redirect:login";
    }

    @PostMapping("/manager/auth")
    @ResponseBody
    public ResponseDao auth(@RequestBody Manager manager, HttpSession session) {
        ResponseDao result = new ResponseDao();
        try {
            Manager curManager = managerService.login(manager.getLoginName(), manager.getPassword());
            if (curManager != null) {
                session.setMaxInactiveInterval(6000);
                session.setAttribute(sessionKey, curManager);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(-1);
        result.setMsg("认证失败");
        return result;
    }

    @GetMapping("/manager/getCurManager")
    @ResponseBody
    public ResponseDao getCurManager(HttpSession session) {
        ResponseDao result = new ResponseDao();
        Object curManager = session.getAttribute(sessionKey);
        if (curManager != null) {
            result.setData(curManager);
            return result;
        }
        result.setCode(-1);
        return result;
    }

    /**
     * 用于安卓端登录
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/test/login")
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (username.equals("dsc") && password.equals("1")) {
            return "{\"result\":\"true\"}";
        }
        return "{\"result\":\"false\"}";
    }
}
