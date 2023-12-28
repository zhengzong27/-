package com.book.app.controller;

import com.book.app.aop.SystemControllerLog;
import com.book.app.bean.Category;
import com.book.app.service.CategoryService;
import com.book.app.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "图书类别API文档")
@RestController
@RequestMapping("/category")
@CrossOrigin   //主要作用就是用来处理跨服务器访问数据的问题，该标签没有服务器之间是数据传递的
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //该方法它给客户端返回的数据格式是必须是json格式
    @ApiOperation("查询图书类别信息")
    @GetMapping("/list")
    public CommonResult listPager(int page,int size,String catename) {
        Map<String,Object> map=new HashMap<>();
        map.put("data",categoryService.listPager(page,size,catename));
        map.put("total",categoryService.count(catename));
        return CommonResult.success(map);
    }
    @SystemControllerLog(description = "添加图书类别信息")
    @ApiOperation("根据ID删除图书类别信息")
    @DeleteMapping("/remove")
    public CommonResult  remove(int cateid) {
        return CommonResult.success(categoryService.removeCategory(cateid));
    }


    @SystemControllerLog(description = "添加类别信息")
    @ApiOperation("添加图书类别信息")
    @PostMapping("/save")
    public CommonResult save(@RequestBody Category category) {
        return CommonResult.success(categoryService.saveCategory(category));
    }
    @SystemControllerLog(description = "修改图书类别信息")
    @ApiOperation("修改图书类别信息")
    @PutMapping("/update")
    public CommonResult update(@RequestBody Category category) {
        return CommonResult.success(categoryService.updateCategory(category));
    }
    @ApiOperation("查询所有类别信息")
    @GetMapping("/searchCategories")
    public CommonResult searchCategories(){
        return CommonResult.success(categoryService.searchCategories());
    }
}
