package com.dsc.springboot.controller;

import com.dsc.springboot.model.User;
import com.dsc.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Map<String, Object> resultMap = new HashMap<>();

    @GetMapping("/getUsers")
    private Map<String, Object> getUsers(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        resultMap.clear();
        resultMap.put("code", 0);
        resultMap.put("msg", "success");
        resultMap.put("count", userService.getUserCount());
        resultMap.put("data", userService.getUsers(page, limit));
        return resultMap;
    }

    @GetMapping("/getUserCount")
    private int getUserCount() {
        return userService.getUserCount();
    }

    @GetMapping("/getUser/{id}")
    public Map<String, Object> getUser(@PathVariable("id") Integer id) {
        resultMap.clear();
        resultMap.put("code", 0);
        resultMap.put("msg", "success");
        resultMap.put("data", userService.getUserById(id));
        return resultMap;
    }

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) throws Exception {
        int i = userService.addUser(user);
        System.out.println(i);
        if (i == 0) {
            return false;
        }
        return true;
    }

    @PutMapping("/update")
    public boolean updateUser(@RequestBody User user) {
        int i = userService.updateUser(user);
        System.out.println(i);
        if (i == 0) {
            return false;
        }
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") Integer id) {
        int i = userService.deleteUser(id);
        System.out.println(i);
        if (i == 0) {
            return false;
        }
        return true;
    }

    @GetMapping("/test")
    public void test() throws Exception {
        try {
            userService.deleteUser(121);
            userService.deleteUser(122);
            throw new Exception();
        } catch (Exception e) {
            System.out.println("捕获到异常！！！");
        }
    }
}
