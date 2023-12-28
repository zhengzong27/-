package com.book.app.bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private Integer cateid;
    private String catename;
    private String createtime;
    private String modifytime;
    private String deleted;
}
