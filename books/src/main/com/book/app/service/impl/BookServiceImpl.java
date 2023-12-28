package com.book.app.service.impl;

import com.book.app.dao.BookDao;
import com.book.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.app.bean.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public List<Book> listPager(int page, int size, String bname) {
        return bookDao.listPager((page-1)*size,size,bname);
    }

    public int count(String bname) {
        return bookDao.count(bname);
    }


    public boolean removeBook(int bid) {
        return bookDao.delete(bid)>0?true:false;
    }

    @Override
    public boolean save(Book book) {
        return bookDao.save(book)>0;
    }

    @Override
    public boolean update(Book book) {
        return bookDao.update(book)>0;
    }

    @Override
    public List<Book> listBooks() {
        return bookDao.listBook();
    }


}
