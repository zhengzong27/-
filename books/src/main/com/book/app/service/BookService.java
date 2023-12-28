package com.book.app.service;


import org.apache.ibatis.annotations.Param;

import com.book.app.bean.Book;
import java.util.List;

public interface BookService {
    List<Book> listPager(int page, int size, String bname);

    int count(String catename);
    boolean  removeBook(int bid);

    boolean save(Book book);

    boolean update(Book book);

    List<Book> listBooks();
}
