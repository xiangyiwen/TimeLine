package com.example.timeline.Repository;

import com.example.timeline.Do.Comment;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CommentDaoTest {
    @Autowired
    private CommentDao commentDao;
    private Comment c;
    int originlength;   //记录测试前数据库中有几条记录

    @Before
    public void init(){
        originlength = commentDao.getComments().size();
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = f.format(date);
        c = new Comment(100,"xiangyiwen","success",time);
        commentDao.deleteByname("xiangyiwen");
        commentDao.comAdd(c.getId(),c.getName(),c.getContent(),c.getTime());
    }

    @After
    public void cleanup() {
        commentDao.deleteByname("xiangyiwen");
    }

    @Test
    public void TestAddComment() throws SQLException {
        List<Comment> res = commentDao.findByname("xiangyiwen");

        int count = res.size()-1;
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals(c.getContent(),res.get(count).getContent(),"Comment内容有误"),
                ()->assertEquals(c.getName(),res.get(count).getName(),"用户名有误"),
                ()->assertEquals(c.getTime(),res.get(count).getTime(),"Comment创建时间有误")
        );
    }

    @Test
//    @DisplayName("测试返回的数据")
    public void TestGetAllComments() throws SQLException {
        List<Comment> results = commentDao.getComments();
        assertAll(
                ()->assertNotNull(results,"结果为空！"),
                ()->assertEquals("xiangyiwen",results.get(originlength).getName()),
                ()->assertEquals(c.getTime(),results.get(originlength).getTime()),
                ()->assertEquals("success",results.get(originlength).getContent())
        );

        assertEquals(originlength+1,results.size(),"Comment的数量不符合！");
    }


//    @Test
//    public void TestFindUser1() throws SQLException{
//        List<Comment> res = commentDao.findByname("用户1");
//        assertAll(
//                ()->assertNotNull(res),
//                ()->assertEquals("用户1",res.get(0).getName()),
//                ()->assertEquals("想要在项目启动时初始化一些静态变量(从数据库里查出来的)" +
//                        "首先想到了使用 static{ }静态代码块, 但是静态代码块的执行顺序在@Autowired注入的Service层之前。" +
//                        "也就是说要在静态代码块中访问数据库, 但是执行静态代码块中的代码时,@Autowired还没有注入进来,会报NullPoint。",res.get(0).getContent()),
//                ()->assertEquals("2019-01-12 07:24",res.get(0).getTime())       //assertEquals比较long类型数据的时候，需要在数据的末尾加上L
//        );
//    }

}