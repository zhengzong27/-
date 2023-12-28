package com.book.app.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book  implements Serializable {
    private Integer bid;
    private Integer cateid;
    private String bnum;
    private String bname;
    private Integer price;
    private String author;
    private String ddesc;
    private Integer quantity;
    private String createtime;
    private String modifytime;
    private Integer deleted;
    private String catename;






}
