package com.book.app.service;

import com.book.app.bean.Logs;


import java.util.List;

public interface LogsService {


    List<Logs> listPager( int page,  int size);
    //统计分页记录数
    int count();

    boolean save(Logs logs);
}
