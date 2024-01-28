package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Article;

import java.util.List;


@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            " values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime}" +
            ",#{updateTime})")
    void add(Article article);
    //分页查询  动态sql
    List<Article> list(Integer userid, Integer categoryId, String state);

    //删除文章
    @Delete("delete from article where id =#{id}")
    void delete(Integer id);
    //修改文章
    @Update("update article set title=#{title},content=#{content},category_id=#{categoryId}" +
            ",cover_img=#{coverImg},state=#{state} where id = #{id}")
    void update(Article article);
}
