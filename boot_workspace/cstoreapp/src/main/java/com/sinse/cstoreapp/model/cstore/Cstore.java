package com.sinse.cstoreapp.model.cstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cstore {
    @JsonProperty("SIGUN_NM")
    private String sigun_nm;
    @JsonProperty("BIZPLC_NM")
    private String bizplc_nm;
    @JsonProperty("BSN_STATE_NM")
    private String bsn_state_nm;
    @JsonProperty("REFINE_ROADNM_ADDR")
    private String refine_roadnm_addr;
}
