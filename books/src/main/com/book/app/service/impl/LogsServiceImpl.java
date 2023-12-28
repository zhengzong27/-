package com.book.app.service.impl;

import com.book.app.bean.Logs;
import com.book.app.dao.LogsDao;
import com.book.app.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsServiceImpl  implements LogsService {
    @Autowired
    private LogsDao logsDao;
    @Override
    public List<Logs> listPager(int page, int size) {
        return logsDao.listPager((page-1)*size,size);
    }

    @Override
    public int count() {
        return logsDao.count();
    }

    @Override
    public boolean save(Logs logs) {
        return logsDao.save(logs)>0;
    }
}
