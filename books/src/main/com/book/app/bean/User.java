package com.book.app.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  implements Serializable {
    private Integer uid;
    private String password;
    private String uname;
    private String images;
    private String stats;
    private String createtime;
    private String modifytime;
    private Integer deleted;
}
