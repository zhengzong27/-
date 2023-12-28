package com.book.app.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Logs implements Serializable {
    private Integer operid;
    private String opertime;
    private String ip;
    private String methods;
    private String ddesc;
}
