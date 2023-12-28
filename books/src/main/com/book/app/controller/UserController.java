package com.book.app.controller;

import com.book.app.bean.User;
import com.book.app.service.BookService;
import com.book.app.service.UserService;
import com.book.app.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "图书馆藏API文档")
@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin   //主要作用就是用来处理跨服务器访问数据的问题，该标签没有服务器之间是数据传递的
public class UserController {
    @Autowired
    private UserService userService;
    //该方法它给客户端返回的数据格式是必须是json格式
    @ApiOperation("用管理户信息")
    @GetMapping("/list")
    public CommonResult listPager(int page,int size,String uname){
        log.info("查询用户信息,{},{},{}",page,size,uname);
        Map<String,Object> map=new HashMap<>();
        map.put("data",userService.listPager(page,size,uname));
        map.put("total",userService.count(uname));
        return CommonResult.success(map);
    }
    @ApiOperation("根据id删除馆藏图书信息")
    @DeleteMapping("/remove")
    public CommonResult remove(int uid){
        return  CommonResult.success(userService.removeUser(uid));
    }
    @ApiOperation("添加馆藏图书信息")
    @PostMapping("/save")
    public CommonResult save(@RequestBody User user) {
        return CommonResult.success(userService.save(user));
    }

    @ApiOperation("修改馆藏图书信息")
    @PutMapping("/update")
    public CommonResult update(@RequestBody User user) {
        return CommonResult.success(userService.update(user));
    }


}

