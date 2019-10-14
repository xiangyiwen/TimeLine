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
        List<Comment> comments = new ArrayList<>();
        Comment c = new Comment("用户28","just for try",new Date().getTime());
        comments.add(c);
        when(commentDao.getComments()).thenReturn(comments);

        commentService.comAdd(28);
        List<Comment> des = commentService.getComments(1);
        assertAll(
                ()->assertEquals("用户28",des.get(0).getName()),
                ()->assertEquals("just for try",des.get(0).getContent()),
                ()->assertEquals(new Date().getTime()/1000,des.get(0).getTime()/1000,"时间出现错误")
        );
    }

    @Test
    @DisplayName(value = "测试返回的格式")
    public void getCommentsTest() {
        List<Comment> comments = new ArrayList<>();
        Comment c1 = new Comment("用户1","content1",new Date().getTime());
        Comment c2 = new Comment("用户2","content2",new Date().getTime());
        comments.add(c1);
        comments.add(c2);
        when(commentDao.getComments()).thenReturn(comments);

        List<Comment> des = commentService.getComments(2);
        assertAll(
                ()->assertEquals("用户2",des.get(0).getName(),"List反转失败"),
                ()->assertEquals("content2",des.get(0).getContent()),
                ()->assertEquals("用户1",des.get(1).getName()),
                ()->assertEquals("content1",des.get(1).getContent())
        );
    }


}