package com.example.timeline.Repository;

import com.example.timeline.Do.Comment;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CommentDaoTest {
    @Autowired
    private CommentDao commentDao;


    @Test
    @DisplayName("测试是否成功向数据库增加Comment")
    public void addCommentTest() throws SQLException {
        Comment c = new Comment("xiangyiwen","success",new Date().getTime());
        commentDao.comAdd("xiangyiwen","success",new Date().getTime());
        List<Comment> res = commentDao.findByname("xiangyiwen");
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals("success",res.get(0).getContent(),"成功增加一条Comment")
        );
    }

    @Test
    @DisplayName("测试返回的数据")
    public void getCommentsTest() throws SQLException {
        List<Comment> res = commentDao.getComments();
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals(12,res.size(),"成功增加一条Comment"),
                ()->assertEquals("Sherry",res.get(0).getName())
        );
    }

}