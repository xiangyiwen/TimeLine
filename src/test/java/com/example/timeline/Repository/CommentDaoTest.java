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
import java.text.SimpleDateFormat;
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
    @DisplayName("测试选择是否有效")
    public void findBynameTset(){
        List<Comment> res = commentDao.findByname("用户1");
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals("用户1",res.get(0).getName()),
                ()->assertEquals("想要在项目启动时初始化一些静态变量(从数据库里查出来的)" +
                        "首先想到了使用 static{ }静态代码块, 但是静态代码块的执行顺序在@Autowired注入的Service层之前。" +
                        "也就是说要在静态代码块中访问数据库, 但是执行静态代码块中的代码时,@Autowired还没有注入进来,会报NullPoint。",res.get(0).getContent()),
                ()->assertEquals("2019-01-12 07:24",res.get(0).getTime())
                //assertEquals比较long类型数据的时候，需要在数据的末尾加上L
        );
    }

    @Test
    @DisplayName("测试是否成功向数据库增加Comment")
    public void addCommentTest() throws SQLException {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = f.format(date);

        Comment c = new Comment("xiangyiwen","success",time);
        commentDao.comAdd(c.getName(),c.getContent(),c.getTime());
        List<Comment> res = commentDao.findByname("xiangyiwen");
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals(c.getContent(),res.get(0).getContent(),"Comment内容有误"),
                ()->assertEquals(c.getName(),res.get(0).getName(),"用户名有误"),
                ()->assertEquals(c.getTime(),res.get(0).getTime(),"Comment创建时间有误")
        );
    }

    @Test
    @DisplayName("测试返回的数据")
    public void getCommentsTest() throws SQLException {
        List<Comment> res = commentDao.getComments();
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals("Sherry",res.get(0).getName()),
                ()->assertEquals("2018-11-24 11:24",res.get(0).getTime()),
                ()->assertEquals("用于测试",res.get(0).getContent()),
                ()->assertEquals(-1,res.get(0).getTime().compareTo(res.get(1).getTime()),"创建Comment的时间不满足逻辑"),
                ()->assertEquals(-1,res.get(1).getTime().compareTo(res.get(2).getTime())),
                ()->assertEquals(-1,res.get(2).getTime().compareTo(res.get(3).getTime()))
        );
    }

}