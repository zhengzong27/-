package com.book.app.controller;

import com.book.app.aop.SystemControllerLog;
import com.book.app.bean.Borrow;
import com.book.app.bo.BorrowBo;
import com.book.app.service.BorrowService;
import com.book.app.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags="借阅信息API文档")
@RestController
@RequestMapping("/borrow")
@CrossOrigin
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @ApiOperation("查询借阅信息")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "page",value ="分页起始数据",required = true,example = "1"),
            @ApiImplicitParam (name = "size",value ="每页显示最大条数",required = true,example = "1"),
    })
    @GetMapping("/list")
    public CommonResult list(int page,int size,String name,String bname){
        Map<String,Object> map=new HashMap<>();
        map.put("data",borrowService.list(page,size,name,bname));
        map.put("total",borrowService.count(name,bname));
        return CommonResult.success(map);
    }
    @SystemControllerLog(description = "添加借阅图书信息")
    @ApiOperation("添加借阅信息")
    @PostMapping("/borrowBook")
    public CommonResult borrowBook(@RequestBody Borrow borrow){
        return CommonResult.success(borrowService.borrowBook(borrow));
    }

    @SystemControllerLog(description = "归还图书信息")
    @ApiOperation("归还图书")
    @PutMapping("/backBook")
    public CommonResult backBook(@RequestBody Borrow borrow){
        return CommonResult.success(borrowService.backBook(borrow));
    }
    @ApiOperation("统计借阅人借阅图书的数量")
    @GetMapping("/getNamesBorrowCount")
    public CommonResult getNamesBorrowCount() {
        return CommonResult.success(borrowService.getNamesBorrowCount());
    }
}
