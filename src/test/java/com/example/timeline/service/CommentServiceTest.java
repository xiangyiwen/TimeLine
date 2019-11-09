package com.example.timeline.service;

import com.example.timeline.Do.Comment;
import com.example.timeline.Repository.CommentDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import com.example.timeline.service.CommentService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;




@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CommentServiceTest {
    @Mock
    private CommentDao commentDao;

    @InjectMocks
    private CommentService commentService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName(value = "测试是否成功增加")
    public void addContentTest() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = f.format(date);

        List<Comment> comments = new ArrayList<>();
        Comment c = new Comment("用户28","just for test",time);
        comments.add(c);
        when(commentDao.getComments()).thenReturn(comments);

        commentService.comAdd(28);
        List<Comment> des = commentService.getComments(1);
        assertAll(
                ()->assertEquals("用户28",des.get(0).getName()),
                ()->assertEquals("just for test",des.get(0).getContent()),
                ()->assertEquals(c.getTime(),des.get(0).getTime(),"时间出现错误")
        );
    }

    @Test
    @DisplayName(value = "测试返回的格式")
    public void getCommentsTest() {
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        List<Comment> comments = new ArrayList<>();
        Comment c1 = new Comment("用户1","content1",f.format(date1));
        Comment c2 = new Comment("用户2","content2",f.format(date2));
        Comment c3 = new Comment("用户3","content3",f.format(date3));
        comments.add(c1);
        comments.add(c2);
        comments.add(c3);
        when(commentDao.getComments()).thenReturn(comments);

        List<Comment> des = commentService.getComments(2);
        assertAll(
                ()->assertEquals(2,des.size(),"参数为2，应该只返回两个Comment"),
                ()->assertEquals("用户3",des.get(0).getName(),"List反转失败"),
                ()->assertEquals("content3",des.get(0).getContent()),
                ()->assertEquals(c3.getTime(),des.get(0).getTime()),
                ()->assertEquals("用户2",des.get(1).getName()),
                ()->assertEquals("content2",des.get(1).getContent()),
                ()->assertEquals(c2.getTime(),des.get(1).getTime()),

                ()->assertTrue(des.get(0).getTime().compareTo(des.get(1).getTime())==1||des.get(0).getTime().compareTo(des.get(1).getTime())==0,"创建Comment的时间不满足逻辑")
        );
    }


}