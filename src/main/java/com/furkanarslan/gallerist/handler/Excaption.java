package com.furkanarslan.gallerist.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Excaption<E> {

    private String path;
    private Date createTime;
    private String Hostname;
    private E message;

}
