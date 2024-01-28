package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ArticleMapper;
import org.example.pojo.Article;
import org.example.pojo.PageBean;
import org.example.service.ArticleService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    //新增文章
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        article.setCreateUser(userid);
        articleMapper.add(article);

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建PageBean对象
        PageBean<Article> pb = new PageBean<>();
        //开启分页查询 PageHelper插件
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper执行sql
         Map<String,Object> mp = ThreadLocalUtil.get();
         Integer userid = (Integer) mp.get("id");
        List<Article> as = articleMapper.list(userid,categoryId,state);
      //page中提供了方法，可以获取PageHelper分页查询行，得到的总记录条数和当前页数据
        //打开try-catch的快捷键是ctrl+alt+t
        Page<Article> p = (Page<Article>) as;
        //将数据封装到PageBean对象
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;

    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }
}
