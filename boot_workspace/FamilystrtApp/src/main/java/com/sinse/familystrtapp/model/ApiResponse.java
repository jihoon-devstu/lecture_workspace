package com.sinse.familystrtapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class ApiResponse {

    @JsonProperty("Familyrstrt")
    private List<Familyrstrt> Familyrstrt;

    @Data
    public static class Familyrstrt {
        private List<Head> head;
        private List<Row> row; // row가 ApiResponse.Row 구조
    }

    @Data
    public static class Head {
        @JsonProperty("list_total_count")
        private int listTotalCount;

        @JsonProperty("RESULT")
        private Result result;

        @JsonProperty("api_version")
        private String apiVersion;
    }

    @Data
    public static class Result {
        @JsonProperty("CODE")
        private String code;

        @JsonProperty("MESSAGE")
        private String message;
    }

    @Data
    public static class Row {
        @JsonProperty("SIGUN_NM")
        private String sigunNm;

        @JsonProperty("SIGUN_CD")
        private String sigunCd;

        @JsonProperty("BIZPLC_NM")
        private String bizplcNm;

        @JsonProperty("LICENSG_DE")
        private String licensgDe;

        @JsonProperty("BSN_STATE_NM")
        private String bsnStateNm;

        @JsonProperty("CLSBIZ_DE")
        private String clsbizDe;

        @JsonProperty("LOCPLC_AR")
        private Double locplcAr;

        @JsonProperty("BULDNG_POSESN_DIV_NM")
        private String buldngPosesnDivNm;

        @JsonProperty("GRAD_FACLT_DIV_NM")
        private String gradFacltDivNm;

        @JsonProperty("YY")
        private Integer yy;

        @JsonProperty("MULTI_USE_BIZESTBL_YN")
        private String multiUseBizestblYn;

        @JsonProperty("TOT_FACLT_SCALE")
        private Double totFacltScale;

        @JsonProperty("SANITTN_INDUTYPE_NM")
        private String sanittnIndutypeNm;

        @JsonProperty("SANITTN_BIZCOND_NM")
        private String sanittnBizcondNm;

        @JsonProperty("TOT_EMPLY_CNT")
        private Integer totEmplyCnt;

        @JsonProperty("REFINE_LOTNO_ADDR")
        private String refineLotnoAddr;

        @JsonProperty("REFINE_ROADNM_ADDR")
        private String refineRoadnmAddr;

        @JsonProperty("REFINE_ZIP_CD")
        private String refineZipCd;

        @JsonProperty("REFINE_WGS84_LOGT")
        private Double refineWgs84Logt;

        @JsonProperty("REFINE_WGS84_LAT")
        private Double refineWgs84Lat;
    }
}