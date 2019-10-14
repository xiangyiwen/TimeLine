package com.example.timeline.Repository;

import com.example.timeline.Do.Comment;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {
//    这里不能用interval，这个是MySQL的关键词，巨坑
    @Insert("INSERT INTO comment(name,content,time) VALUES(#{name}, #{content}, #{time})")
    void comAdd(@Param("name") String name, @Param("content") String content, @Param("time") long time);

    @Select("SELECT * FROM comment")
    List<Comment> getComments();

    @Select("SELECT * FROM comment where name = #{name}")
    List<Comment> findByname(@Param("name") String name);
}
