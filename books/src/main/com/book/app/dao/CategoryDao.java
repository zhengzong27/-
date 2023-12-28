package com.book.app.dao;

import com.book.app.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问层接口，该接口使用mybatis技术访问数据库
 */
@Repository
public interface CategoryDao {


    //分页查询方法
    List<Category>listPager(@Param("page") int page, @Param("size") int size,
                            @Param("catename")String catename);
//统计分页记录数
    int count(@Param("catename") String catename);

    int insert(Category category);

    int   update(Category category);

    int  delete(int cid);

    List<Category> searchCategories();
}
