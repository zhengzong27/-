package com.book.app.controller;

import com.book.app.aop.SystemControllerLog;
import com.book.app.bean.Book;
import com.book.app.bean.Category;
import com.book.app.service.BookService;
import com.book.app.service.CategoryService;
import com.book.app.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "图书馆藏API文档")
@RestController
@RequestMapping("/book")
@CrossOrigin   //主要作用就是用来处理跨服务器访问数据的问题，该标签没有服务器之间是数据传递的
public class BookController {
    @Autowired
    private BookService bookService;
    //该方法它给客户端返回的数据格式是必须是json格式
    @ApiOperation("查询图书馆藏类别信息")
    @GetMapping("/list")
    public CommonResult listPager(int page,int size,String bname){
        Map<String,Object> map=new HashMap<>();
        map.put("data",bookService.listPager(page,size,bname));
        map.put("total",bookService.count(bname));
        return CommonResult.success(map);
    }
    @SystemControllerLog(description = "删除图书信息")
    @ApiOperation("根据id删除馆藏图书信息")
    @DeleteMapping("/remove")
    public CommonResult remove(int bid){
        return  CommonResult.success(bookService.removeBook(bid));
    }

    @SystemControllerLog(description = "添加图书信息")
    @ApiOperation("添加馆藏图书信息")
    @PostMapping("/save")
    public CommonResult save(@RequestBody Book book) {
        return CommonResult.success(bookService.save(book));
    }

    @SystemControllerLog(description = "修改图书信息")
    @ApiOperation("修改馆藏图书信息")
    @PutMapping("/update")
    public CommonResult update(@RequestBody Book book) {
        return CommonResult.success(bookService.update(book));
    }
    @ApiOperation("查看所有馆藏图书信息")
    @GetMapping("/listBook")
    public CommonResult listBook(){
        return CommonResult.success(bookService.listBooks());
    }


}

