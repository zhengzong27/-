package com.book.app.service;

import com.book.app.bean.Category;

import java.awt.print.Book;
import java.util.List;

public interface CategoryService {

    List<Category>listPager(int page,int size,String catename);
    int count(String catename);

    boolean   saveCategory(Category category);

    boolean   updateCategory(Category category);

    boolean  removeCategory(int cateid);

    List<Category> searchCategories();
}
