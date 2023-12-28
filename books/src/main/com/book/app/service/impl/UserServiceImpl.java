package com.book.app.service.impl;

import com.book.app.bean.Book;
import com.book.app.bean.User;
import com.book.app.dao.BookDao;
import com.book.app.dao.UserDao;
import com.book.app.service.BookService;
import com.book.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public List<User> listPager(int page, int size, String uname) {
        return userDao.listPager((page-1)*size,size,uname);
    }

    public int count(String uname) {
        return userDao.count(uname);
    }

    public boolean removeUser(int uid) {
        return userDao.delete(uid)>0?true:false;
    }

    @Override
    public boolean save(User user) {
        return userDao.save(user)>0;
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user)>0;
    }



}
