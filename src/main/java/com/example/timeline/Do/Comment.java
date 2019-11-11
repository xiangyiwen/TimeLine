package com.example.timeline.Do;

import lombok.Data;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;
    @Column(name = "time")
    private String time;

    public Comment() {  //一定需要写一个不带参数的构造函数
        super();
    }

    public Comment(int id,String name,String content,String time){
        super();
        this.id = id;
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public int getId() {
    return id;
}
    public String getContent() {
        return content;
    }
    public String getTime() {
        return time;
    }
}
