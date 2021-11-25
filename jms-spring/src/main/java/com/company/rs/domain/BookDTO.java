package com.company.rs.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDTO implements Serializable {
    public static final long serialVersionUID = 42L;
    private String author;
    private String title;
    private int price;
}
