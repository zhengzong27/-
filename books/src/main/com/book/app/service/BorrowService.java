package com.book.app.service;

import com.book.app.bean.Borrow;
import com.book.app.bo.BorrowBo;

import java.util.*;
public interface BorrowService {
    List<Borrow> list(int page, int size, String name, String bname);

    int count(String name, String bname);

    boolean borrowBook(Borrow borrow);

    boolean backBook(Borrow borrow);

    /**
     *
     * @return
     */
    List<BorrowBo>getNamesBorrowCount();
}
