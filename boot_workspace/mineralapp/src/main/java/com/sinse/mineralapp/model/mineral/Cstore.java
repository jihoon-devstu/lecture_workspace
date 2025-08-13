package com.sinse.mineralapp.model.mineral;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cstore {

    @JsonProperty("title")
    private String title;          // 제목

    @JsonProperty("ingredient")
    private String ingredient;     // 특이성분

    @JsonProperty("posx")
    private double posx;           // 경도

    @JsonProperty("posy")
    private double posy;           // 위도

    @JsonProperty("waterBase")
    private String waterBase;      // 수질평가

    @JsonProperty("waterType")
    private String waterType;      // 수질특성
}
