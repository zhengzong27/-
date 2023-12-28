package com.book.app.controller;

import com.book.app.service.LogsService;
import com.book.app.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Api(tags = "日志API文档")
@RestController
@RequestMapping("/logs")
@CrossOrigin
public class LogsController {
    @Autowired
    private LogsService logsService;

    @ApiOperation("查询所有操作日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "分页起始数据",required = true,example ="1" ),
            @ApiImplicitParam(name = "size",value = "每页显示最大条数",required = true,example ="1" ),
    })
    @GetMapping("/list")
    public CommonResult listPager(int page, int size){
        Map<String,Object> map=new HashMap<>();
        map.put("data",logsService.listPager(page,size));
        map.put("total",logsService.count());
        return CommonResult.success(map);
    }
}
