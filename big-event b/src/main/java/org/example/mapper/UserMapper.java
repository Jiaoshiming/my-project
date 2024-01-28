package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.User;

@Mapper
public interface UserMapper {
//用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);
//添加注册
    @Insert("insert into user(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void add(String username, String password);
//更新信息
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id = #{id}")
    void update(User user);
//更新头像
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id =#{id}")
    void updateAvater(String avatarUrl,Integer id);
    //更换密码
    @Update("update user set password=#{md5String},update_time=now() where id=#{id} ")
    void updatePwd(String md5String,Integer id);
}
