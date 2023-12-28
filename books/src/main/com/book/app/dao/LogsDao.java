package com.book.app.dao;

import com.book.app.bean.Book;
import com.book.app.bean.Logs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsDao {

    int save(Logs logs);

    List<Logs> listPager(@Param("page") int page, @Param("size") int size);
    //统计分页记录数
    int count();
}
