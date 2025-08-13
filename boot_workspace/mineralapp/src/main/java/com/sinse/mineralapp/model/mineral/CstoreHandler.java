package com.sinse.mineralapp.model.mineral;

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
    private List<Cstore> cstoreList;

    Cstore cstore;

    private boolean isTitle =false;
    private boolean isIngredient =false;
    private boolean isposx =false;
    private boolean isposy =false;
    private boolean isWaterBase =false;
    private boolean isWaterType =false;

    @Override
    public void startDocument() throws SAXException {
        cstoreList =new ArrayList<Cstore>();
    }

    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        if(tag.equals("mineral")){
            cstore =new Cstore();
        }else if(tag.equals("title")){
            isTitle=true;
        }else if(tag.equals("ingredient")){
            isIngredient=true;
        }else if(tag.equals("posx")){
            isposx=true;
        }else if(tag.equals("posy")){
            isposy=true;
        }else if(tag.equals("waterbase")){
            isWaterBase=true;
        }else if(tag.equals("watertype")){
            isWaterType=true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content=new String(ch,start,length);

        if(isTitle){
            cstore.setTitle(content);
        }
        if(isIngredient){
            cstore.setIngredient(content);
        }
        if(isposx){
            cstore.setPosx(Double.parseDouble(content));
        }
        if(isposy){
            cstore.setPosy(Double.parseDouble(content));
        }
        if(isWaterBase){
            cstore.setWaterBase(content);
        }
        if(isWaterType){
            cstore.setWaterType(content);
        }

    }

    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        if(tag.equals("mineral")){
            cstoreList.add(cstore);
        }else if(tag.equals("title")){
            isTitle=false;
        }else if(tag.equals("ingredient")){
            isIngredient=false;
        }else if(tag.equals("posx")){
            isposx=false;
        }else if(tag.equals("posy")){
            isposy=false;
        }else if(tag.equals("waterbase")){
            isWaterBase=false;
        }else if(tag.equals("watertype")){
            isWaterType=false;
        }
    }
}
