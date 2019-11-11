package com.example.timeline.Repository;

import com.example.timeline.Do.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {
//    这里不能用interval，这个是MySQL的关键词，巨坑
    @Insert("INSERT INTO comment(id,name,content,time) VALUES(#{id}, #{name}, #{content}, #{time})")
    void comAdd(@Param("id") int id, @Param("name") String name, @Param("content") String content, @Param("time") String time);

    @Select("SELECT * FROM comment")
    List<Comment> getComments();

    @Select("SELECT * FROM comment where name = #{name}")
    List<Comment> findByname(@Param("name") String name);
}
