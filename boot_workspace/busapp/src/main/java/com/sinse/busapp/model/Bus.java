package com.sinse.busapp.model;

import lombok.Data;

@Data
public class Bus {

private String bstopnm; //정류소 명
private int arsno; //정류소 번호
private double gpsx; //정류소 위도
private double gpsy; //정류소 경도

}
