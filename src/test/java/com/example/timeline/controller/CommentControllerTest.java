package com.example.timeline.controller;

import com.example.timeline.Do.Comment;
import com.example.timeline.Repository.CommentDao;
import com.example.timeline.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
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

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@WebMvcTest
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CommentService commentService;

    @MockBean
    CommentController commentController;

//    @Mock
//    private CommentService commentService;
//
//    @InjectMocks
//    private CommentController commentController;

//    @Before
//    public void setUp() throws Exception{
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    @DisplayName("更新comment")
    public void Testuploading() {
        ResultActions perform = mockMvc.perform(get("/load"));
        perform.andExpect(status().isOk());

        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        List<Comment> comments = new ArrayList<>();
        Comment c3 = new Comment(3,"用户3","content3",f.format(date3));
        Comment c2 = new Comment(2,"用户2","content2",f.format(date2));
        Comment c1 = new Comment(1,"用户1","content1",f.format(date1));
        comments.add(c3);
        comments.add(c2);
        comments.add(c1);
        when(commentService.getComments(3)).thenReturn(comments);
        doNothing().when(commentService).comAdd(anyInt());

        List<Comment> res = commentController.upload();

        verify(commentService,times(1)).comAdd(anyInt());
        verify(commentService,times(1)).getComments(3);
        verifyNoMoreInteractions(commentService);
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals(3,res.size(),"获得Comment的长度应该是3"),
                ()->assertEquals("用户3",res.get(0).getName(),"成功获得Comment"),
                ()->assertEquals("content3",res.get(0).getContent()),
                ()->assertEquals(c3.getTime(),res.get(0).getTime()),
                ()->assertEquals("用户2",res.get(1).getName()),
                ()->assertEquals("content2",res.get(1).getContent()),
                ()->assertEquals(c2.getTime(),res.get(1).getTime()),
                ()->assertEquals("用户1",res.get(2).getName()),
                ()->assertEquals("content1",res.get(2).getContent()),
                ()->assertEquals(c1.getTime(),res.get(2).getTime())
        );
    }

    @Test
    @DisplayName("加载更多comment")
    public void Testloadingmore() {
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        Date date4 = new Date();
        Date date5 = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        List<Comment> comments = new ArrayList<>();
        Comment c5 = new Comment(5,"用户5","content5",f.format(date5));
        Comment c4 = new Comment(4,"用户4","content4",f.format(date4));
        Comment c3 = new Comment(3,"用户3","content3",f.format(date3));
        Comment c2 = new Comment(2,"用户2","content2",f.format(date2));
        Comment c1 = new Comment(1,"用户1","content1",f.format(date1));
        comments.add(c5);
        comments.add(c4);
        comments.add(c3);
        comments.add(c2);
        comments.add(c1);
        when(commentService.getComments(5)).thenReturn(comments);

        List<Comment> res = commentController.load();

        verify(commentService,times(1)).getComments(5);
        verifyNoMoreInteractions(commentService);
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals(5,res.size(),"应该返回5条Comment"),
                ()->assertEquals("用户5",res.get(0).getName()),
                ()->assertEquals("content5",res.get(0).getContent()),
                ()->assertEquals(c5.getTime(),res.get(0).getTime())
        );
    }

}