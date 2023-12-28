package com.book.app.dao;


import com.book.app.bean.Book;
import com.book.app.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> listPager(@Param("page") int page, @Param("size") int size, @Param("uname") String uname);

    int count(@Param("uname") String uname);

    int delete(int bid);

    int save(User user);

    int update(User user);




}
