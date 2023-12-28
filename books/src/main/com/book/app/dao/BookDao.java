package com.book.app.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.book.app.bean.Book;
import java.util.List;

@Repository
public interface BookDao {
    List<Book> listPager(@Param("page") int page, @Param("size") int size, @Param("bname") String bname);

    int count(@Param("bname") String bname);
    int delete(int bid);

    int save(Book book);

    int update(Book book);

    List<Book> listBook();

    Book getBookById(Integer bid);
}
