package com.book.app.service;

import com.book.app.bean.Book;
import com.book.app.bean.User;

import java.util.List;

public interface UserService {
    List<User> listPager(int page, int size, String uname);

    int count(String uname);

    boolean  removeUser(int uid);

    boolean save(User user);

    boolean update(User user);



}
