package com.book.app.dao;

import com.book.app.bean.Book;
import com.book.app.bean.Borrow;
import com.book.app.bo.BorrowBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowDao {
    List<Borrow> list(@Param("page") int page,
                      @Param("size") int size,
                      @Param("name") String name,
                      @Param("bname") String bname);

    int count(
            @Param("name") String name,
            @Param("bname") String bname);


    int borrowBook(Borrow borrow);

    int update(Borrow borrow);
    //统计借阅人借阅图书的数量
    List<BorrowBo>getNamesBorrowCount();
}
