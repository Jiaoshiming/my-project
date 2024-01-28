package org.example.service;

import org.example.pojo.Article;
import org.example.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);
    //分页查询条件
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //删除文章
    void delete(Integer id);
    //修改文章
    void update(Article article);
}
