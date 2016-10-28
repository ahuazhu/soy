package com.smzdm.soy.example;

import java.util.Date;

/**
 * Created by zhengwenzhu on 16/10/28.
 */
public class Reply {

    private String from;

    private String to;

    private Date date;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
