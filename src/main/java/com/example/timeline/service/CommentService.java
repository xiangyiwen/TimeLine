package com.example.timeline.service;

import com.example.timeline.Do.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.timeline.Repository.CommentDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
//    private static int i = 18;
//    private static int j = 8;
    @Autowired
    private CommentDao commentDao;

    /*定义增加的内容----用于upload正向增加方法*/
    public  void comAdd(int i){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = f.format(date);
        commentDao.comAdd("用户"+i,"想要在项目启动时初始化一些静态变量(从数据库里查出来的)首先想到了使用 static{ }静态代码块, " +
                "但是静态代码块的执行顺序在@Autowired注入的Service层之前。也就是说要在静态代码块中访问数据库, " +
                "但是执行静态代码块中的代码时,@Autowired还没有注入进来,会报NullPoint。",time);
    }


    public List<Comment> getComments(int i){
        List<Comment> des;
        des = commentDao.getComments();
        Collections.reverse(des);
        List<Comment> res = new ArrayList<>();
        res.addAll(des.subList(0,Math.min(i,des.size())));
        return res;
    }


    /*定义增加的内容----用于load反向增加方法*/
//    public  void diverseAdd(CommentList commentList){
//        Comment medComment = new Comment();
//        medComment.setName("用户"+j);
//        medComment.setContent("想要在项目启动时初始化一些静态变量(从数据库里查出来的)首先想到了使用 static{ }静态代码块, " +
//                "但是静态代码块的执行顺序在@Autowired注入的Service层之前。也就是说要在静态代码块中访问数据库, " +
//                "但是执行静态代码块中的代码时,@Autowired还没有注入进来,会报NullPoint。");
//        medComment.setInterval(new Date().getTime());
//
//        List<Comment> des;
//        des = commentList.getCommentList();
//        des.add(medComment);
//        commentList.setCommentList(des);
//
//        j--;
//    }

}
