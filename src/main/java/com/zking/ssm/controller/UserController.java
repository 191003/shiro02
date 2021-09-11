package com.zking.ssm.controller;

import com.zking.ssm.model.User;
import com.zking.ssm.service.IUserService;
import com.zking.ssm.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @RequestMapping("/goDel")
    public String goDel(){

        return "delete";
    }

    @RequestMapping("/goUp")
    public String goUp(){

        return "update";
    }




    @RequestMapping("/add")
    public String save(User user){
        String salt = PasswordHelper.createSalt();
        String password = PasswordHelper.createCredentials(user.getPassword(), salt);
        user.setPassword(password);
        user.setSalt(salt);

        int i = userService.insert(user);
        if (i>0){
            return "admin";
        }else {
            return "good";
        }


    }


    @RequestMapping("/delete")
    public Map<String,Object> del(Integer uid){
        Map<String,Object> map=new HashMap<>();
        int i = userService.deleteByPrimaryKey(uid);
        String msg="删除成功";
        if (i<=0){
           msg="删除失败";
        }
        map.put("msg",msg);
        map.put("code",i);
        return map;
    }


    @RequestMapping("/update")
    public String up(User user){
        int i = userService.updateByPrimaryKey(user);

            return "admin";


    }




}
