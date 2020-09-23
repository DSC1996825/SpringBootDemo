package com.dsc.springboot.controllers;

import com.dsc.springboot.dao.ResponseDao;
import com.dsc.springboot.models.User;
import com.dsc.springboot.services.UserService;
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
    public ResponseDao getUser(@PathVariable("id") Integer id) {
        ResponseDao responseDao = new ResponseDao();
        responseDao.setData(userService.getUserById(id));
        responseDao.setMsg("查询成功");
        return responseDao;
    }

    @PostMapping("/add")
    public ResponseDao addUser(@RequestBody User user) throws Exception {
        int i = userService.addUser(user);
        ResponseDao responseDao = new ResponseDao();
        responseDao.setMsg("新增成功");
        if (i == 0) {
            responseDao.setCode(-1);
            responseDao.setMsg("新增失败");
        }
        return responseDao;
    }

    @PutMapping("/update")
    public ResponseDao updateUser(@RequestBody User user) {
        int i = userService.updateUser(user);
        ResponseDao responseDao = new ResponseDao();
        responseDao.setMsg("修改成功");
        if (i == 0) {
            responseDao.setCode(-1);
            responseDao.setMsg("修改失败");
        }
        return responseDao;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDao deleteUser(@PathVariable("id") Integer id) {
        int i = userService.deleteUser(id);
        ResponseDao responseDao = new ResponseDao();
        responseDao.setMsg("删除成功");
        if (i == 0) {
            responseDao.setCode(-1);
            responseDao.setMsg("删除失败");
        }
        return responseDao;
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
