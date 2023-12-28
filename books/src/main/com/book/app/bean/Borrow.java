package com.book.app.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private Integer boid;
    private String name;
    private Integer bid;
    private String bname;
    private String otime;
    private String intime;
    private Integer deleted;
    private Integer stat;
    private String tel;
    private Integer quantity;
}
