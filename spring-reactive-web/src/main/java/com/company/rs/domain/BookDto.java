package com.company.rs.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BookDto implements Serializable {
    static final long serialVersionUID = 1L;
    String title;
    String author;
    int price;
}
