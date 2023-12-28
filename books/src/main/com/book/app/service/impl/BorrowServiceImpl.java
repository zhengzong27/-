package com.book.app.service.impl;

import com.book.app.bean.Book;
import com.book.app.bean.Borrow;
import com.book.app.bo.BorrowBo;
import com.book.app.dao.BookDao;
import com.book.app.dao.BorrowDao;
import com.book.app.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowDao borrowDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Borrow> list(int page, int size, String name, String bname) {
        return borrowDao.list((page-1)*size,size,name,bname);
    }

    @Override
    public int count(String name, String bname) {
        return borrowDao.count(name,bname);
    }

    @Override
    public boolean borrowBook(Borrow borrow) {
        //操作两张表
        //第一步：根据借阅的图书ID查询馆藏信息，获得一个图书对象
        Book book=bookDao.getBookById(borrow.getBid());
        //第二步:根据获得图书对象，获得馆藏数量，和输入的借阅数量进行比对，借阅数量超出馆藏数量不能借出,提示信息
        if(book.getQuantity()<borrow.getQuantity()){
            return false;
        }
        book.setQuantity(book.getQuantity()-borrow.getQuantity());
        bookDao.update(book);
        //第三步:添加借阅信息，存储借阅表中，同时减去Book表中的bnum
        return borrowDao.borrowBook(borrow)>0;

    }

    @Override
    public boolean backBook(Borrow borrow) {
        //操作两张表
        // 根据归还的图书ID查询馆藏信息，获得一个图书对象
        Book book=bookDao.getBookById(borrow.getBid());
        //更新库存数
        book.setQuantity(book.getQuantity()+borrow.getQuantity());
        bookDao.update(book);//更新库存表
        //更新借阅信息表
        return borrowDao.update(borrow)>0;
    }

    @Override
    public List<BorrowBo> getNamesBorrowCount() {
        return borrowDao.getNamesBorrowCount();
    }
}
