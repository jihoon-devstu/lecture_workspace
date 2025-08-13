package com.sinse.cstoreapp.model.cstore;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class CstoreHandler extends DefaultHandler {
    @Getter
    List<Cstore> cstoreList;

    Cstore cstore;

    private boolean issigun_nm = false;
    private boolean isbizplc_nm = false;
    private boolean isbsn_state_nm = false;
    private boolean isrefine_roadnm_addr = false;

    @Override
    public void startDocument() throws SAXException {
        cstoreList = new ArrayList<Cstore>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("row".equals(qName)) {
            cstore = new Cstore();
        } else if ("SIGUN_NM".equals(qName)) {
            issigun_nm = true;
        } else if ("BIZPLC_NM".equals(qName)) {
            isbizplc_nm = true;
        } else if ("BSN_STATE_NM".equals(qName)) {
            isbsn_state_nm = true;
        } else if ("REFINE_ROADNM_ADDR".equals(qName)) {
            isrefine_roadnm_addr = true;
        }
    }



    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        System.out.println("Tag content: " + content);

        if(issigun_nm){
            cstore.setSigun_nm(content);
        }
        if(isbizplc_nm){
            cstore.setBizplc_nm(content);
        }
        if(isbsn_state_nm){
            cstore.setBsn_state_nm(content);
        }
        if(isrefine_roadnm_addr){
            cstore.setRefine_roadnm_addr(content);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("row".equals(qName)) {
            cstoreList.add(cstore);
        } else if ("SIGUN_NM".equals(qName)) {
            issigun_nm = false;
        } else if ("BIZPLC_NM".equals(qName)) {
            isbizplc_nm = false;
        } else if ("BSN_STATE_NM".equals(qName)) {
            isbsn_state_nm = false;
        } else if ("REFINE_ROADNM_ADDR".equals(qName)) {
            isrefine_roadnm_addr = false;
        }
    }


}
