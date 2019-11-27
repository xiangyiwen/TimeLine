package com.example.timeline.controller;

import com.example.timeline.Do.Comment;
import com.example.timeline.Repository.CommentDao;
import com.example.timeline.service.CommentService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentControllerTest {
    @Mock
    private CommentService commentService;      //全部为空

    @InjectMocks
    private CommentController commentController;    //调用的真实代码（只能是per-class）

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);     //初始化一个commentController实例，整个测试类就一个
        // 所以规定测试方法执行的顺序，来避免在产品代码中增加测试逻辑
    }

    @Test
    @DisplayName("更新comment-增加User13")
    public void should_add_User13()  throws Exception {
        doNothing().when(commentService).comAdd(anyInt());

        ArgumentCaptor<Integer> intArgCaptor = ArgumentCaptor.forClass(Integer.class);

        commentController.upload();
        verify(commentService,times(1)).comAdd(intArgCaptor.capture());
        assertEquals(Integer.valueOf(13), intArgCaptor.getValue(),"应该新增用户13");
    }

    @Test
    @DisplayName("更新comment-增加User14")
    public void should_add_User14()  throws Exception {
        doNothing().when(commentService).comAdd(anyInt());

        ArgumentCaptor<Integer> intArgCaptor = ArgumentCaptor.forClass(Integer.class);

//        commentController.upload();   由于测试1已经调用过一次
        commentController.upload();
        verify(commentService,times(1)).comAdd(intArgCaptor.capture());
        assertEquals(Integer.valueOf(14), intArgCaptor.getValue(),"应该新增用户14");
    }

    @Test
    @DisplayName("加载更多comment-第一次")
    public void should_show_five_comments() {
        when(commentService.getComments(anyInt())).thenReturn(null);

        ArgumentCaptor<Integer> intArgCaptor = ArgumentCaptor.forClass(Integer.class);

        commentController.load();
        verify(commentService,times(1)).getComments(intArgCaptor.capture());
        assertEquals(Integer.valueOf(5), intArgCaptor.getValue());
    }


    @Test
    @DisplayName("加载更多comment-第二次")
    public void should_show_seven_comments() {
        when(commentService.getComments(anyInt())).thenReturn(null);

        ArgumentCaptor<Integer> intArgCaptor = ArgumentCaptor.forClass(Integer.class);

//        commentController.load();
        commentController.load();
        verify(commentService,times(1)).getComments(intArgCaptor.capture());
        assertEquals(Integer.valueOf(7), intArgCaptor.getValue());
    }

//    @Test
//    @DisplayName("更新comment")
//    public void should_show_three_comments()  throws Exception {
//
//        Date date1 = new Date();
//        Date date2 = new Date();
//        Date date3 = new Date();
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//        List<Comment> comments = new ArrayList<>();
//        Comment c3 = new Comment(3,"用户3","content3",f.format(date3));
//        Comment c2 = new Comment(2,"用户2","content2",f.format(date2));
//        Comment c1 = new Comment(1,"用户1","content1",f.format(date1));
//        comments.add(c3);
//        comments.add(c2);
//        comments.add(c1);
//        when(commentService.getComments(3)).thenReturn(comments);
//        doNothing().when(commentService).comAdd(anyInt());
//
//        List<Comment> res = commentController.upload();
//
//        verify(commentService,times(1)).comAdd(anyInt());
//        verify(commentService,times(1)).getComments(3);
//        verifyNoMoreInteractions(commentService);
//        assertAll(
//                ()->assertNotNull(res),
//                ()->assertEquals(3,res.size(),"获得Comment的长度应该是3"),
//                ()->assertEquals("用户3",res.get(0).getName(),"成功获得Comment"),
//                ()->assertEquals("content3",res.get(0).getContent()),
//                ()->assertEquals(c3.getTime(),res.get(0).getTime()),
//                ()->assertEquals("用户2",res.get(1).getName()),
//                ()->assertEquals("content2",res.get(1).getContent()),
//                ()->assertEquals(c2.getTime(),res.get(1).getTime()),
//                ()->assertEquals("用户1",res.get(2).getName()),
//                ()->assertEquals("content1",res.get(2).getContent()),
//                ()->assertEquals(c1.getTime(),res.get(2).getTime())
//        );
//    }

//    @Test
//    @DisplayName("加载更多comment")
//    public void should_show_five_comments() {
//        Date date1 = new Date();
//        Date date2 = new Date();
//        Date date3 = new Date();
//        Date date4 = new Date();
//        Date date5 = new Date();
//        Date date6 = new Date();
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//        List<Comment> comments = new ArrayList<>();
//        Comment c6 = new Comment(6,"用户6","content6",f.format(date6));
//        Comment c5 = new Comment(5,"用户5","content5",f.format(date5));
//        Comment c4 = new Comment(4,"用户4","content4",f.format(date4));
//        Comment c3 = new Comment(3,"用户3","content3",f.format(date3));
//        Comment c2 = new Comment(2,"用户2","content2",f.format(date2));
//        Comment c1 = new Comment(1,"用户1","content1",f.format(date1));
//        comments.add(c6);
//        comments.add(c5);
//        comments.add(c4);
//        comments.add(c3);
//        comments.add(c2);
//        comments.add(c1);
//        when(commentService.getComments(5)).thenReturn(comments);
//
//        List<Comment> res = commentController.load();
//
//        verify(commentService,times(1)).getComments(5);
//        verifyNoMoreInteractions(commentService);
//        assertAll(
//                ()->assertNotNull(res),
//                ()->assertEquals(5,res.size(),"应该返回5条Comment"),
//                ()->assertEquals("用户5",res.get(0).getName()),
//                ()->assertEquals("content5",res.get(0).getContent()),
//                ()->assertEquals(c5.getTime(),res.get(0).getTime())
//        );
//    }

}