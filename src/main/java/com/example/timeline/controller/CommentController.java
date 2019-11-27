package com.example.timeline.controller;

import com.example.timeline.Do.Comment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  com.example.timeline.service.CommentService;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    private static int i = 5;
    private static int j = 13;

    public void initial(){
        i = 5;
        j = 13;
    }

//    @Resource
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws  Exception{
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        return sessionFactory.getObject();
//    }

    @GetMapping("/uploadComment")
    public List<Comment> upload(){
        List<Comment> res;
        commentService.comAdd(j);
        res = commentService.getComments(3);
        j++;
        return res;
    }

    @GetMapping("/loadComment")   //显示前3条内容，并且不断增加（显示更多）
    public List<Comment> load(){
        List<Comment> res;
        res = commentService.getComments(i);
        i+=2;
        return res;
    }



//    @GetMapping("/try")
//    public List<Comment> test(){
////        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
////        String date = f.format(new Date());
//
//        Comment medComment = new Comment();
//        medComment.setName("用户");
//        medComment.setContent("content");
//        medComment.setInterval(new Date().getTime());
//
//        List<Comment> des = commentList.getCommentList();
//        des.add(medComment);
//        return des;
//    }
}
