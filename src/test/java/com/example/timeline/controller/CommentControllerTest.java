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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CommentControllerTest {
    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("更新comment")
    public void uploadTest() {
        List<Comment> comments = new ArrayList<>();
        Comment c3 = new Comment("用户3","content3",new Date().getTime());
        Comment c2 = new Comment("用户2","content2",new Date().getTime());
        Comment c1 = new Comment("用户1","content1",new Date().getTime());
        comments.add(c3);
        comments.add(c2);
        comments.add(c1);

        when(commentService.getComments(3)).thenReturn(comments);
        List<Comment> res = commentController.upload();
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals("用户3",res.get(0).getName()),
                ()->assertEquals("content3",res.get(0).getContent()),
                ()->assertEquals(new Date().getTime()/1000,res.get(0).getTime()/1000)
        );
    }

    @Test
    @DisplayName("加载更多comment")
    public void loadTest() {
        List<Comment> comments = new ArrayList<>();
        Comment c5 = new Comment("用户5","content5",new Date().getTime());
        Comment c4 = new Comment("用户4","content4",new Date().getTime());
        Comment c3 = new Comment("用户3","content3",new Date().getTime());
        Comment c2 = new Comment("用户2","content2",new Date().getTime());
        Comment c1 = new Comment("用户1","content1",new Date().getTime());
        comments.add(c5);
        comments.add(c4);
        comments.add(c3);
        comments.add(c2);
        comments.add(c1);

        when(commentService.getComments(5)).thenReturn(comments);
        List<Comment> res = commentController.load();
        assertAll(
                ()->assertNotNull(res),
                ()->assertEquals(5,res.size()),
                ()->assertEquals("用户5",res.get(0).getName()),
                ()->assertEquals("content5",res.get(0).getContent()),
                ()->assertEquals(new Date().getTime()/1000,res.get(0).getTime()/1000)
        );
    }

}