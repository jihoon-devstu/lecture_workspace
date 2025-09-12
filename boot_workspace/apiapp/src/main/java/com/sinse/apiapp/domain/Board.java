package com.sinse.apiapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="board")
@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private int board_id;

    private String title;
    private String writer;
    private String content;

    @Column(name="regdate", insertable=false, updatable=false
    , columnDefinition = "timestamp default current_timestamp")
    private String regdate;

    private int hit;
}
