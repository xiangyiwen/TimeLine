package com.example.timeline.Do;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private String name;
    private String content;
    private long time;


    public Comment(String name,String content,long time){
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }

}
