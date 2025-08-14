package com.sinse.busapp.model;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusHandler extends DefaultHandler {
    @Getter
    List<Bus> busList;

    Bus bus;

    private boolean isBstopnm = false;
    private boolean isArsno = false;
    private boolean isGpsx = false;
    private boolean isGpsy = false;

    @Override
    public void startDocument() throws SAXException {
        busList = new ArrayList<Bus>();
    }

    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        if(tag.equals("item")) {
            bus = new Bus();
        }else if(tag.equals("bstopnm")) {
            isBstopnm = true;
        }else if(tag.equals("arsno")) {
            isArsno = true;
        }else if(tag.equals("gpsx")) {
            isGpsx = true;
        }else if(tag.equals("gpsy")) {
            isGpsy = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        if(isBstopnm) {
            bus.setBstopnm(content);
        }
        if(isArsno) {
            bus.setArsno(Integer.parseInt(content));
        }
        if(isGpsx) {
            bus.setGpsx(Double.parseDouble(content));
        }
        if(isGpsy) {
            bus.setGpsy(Double.parseDouble(content));
        }
    }

    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        if(tag.equals("item")) {
            busList.add(bus);
        }else if(tag.equals("bstopnm")) {
            isBstopnm = false;
        }else if(tag.equals("arsno")) {
            isArsno = false;
        }else if(tag.equals("gpsx")) {
            isGpsx = false;
        }else if(tag.equals("gpsy")) {
            isGpsy = false;
        }
    }
}
