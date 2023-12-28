package com.book.app.service.impl;

import com.book.app.bean.Category;
import com.book.app.dao.CategoryDao;
import com.book.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    //spring的注入标签，该标签的作用实例化CategoryDao对象
    @Autowired
    private CategoryDao categoryDao;
    public List<Category> listPager(int page,int size,String catename) {
        return categoryDao.listPager((page-1)*size,size,catename);
    }
    public int count(String catename){
        return categoryDao.count(catename);
    }

    public boolean saveCategory(Category category) {
        return categoryDao.insert(category)>0?true:false;
    }

    public boolean updateCategory(Category category) {

        return categoryDao.update(category)>0?true:false;
    }

    public boolean removeCategory(int cateid) {

        return categoryDao.delete(cateid)>0?true:false;
    }

    @Override
    public List<Category> searchCategories() {
        return categoryDao.searchCategories();
    }

}
