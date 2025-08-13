package com.sinse.mineralapp.model.mineral;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class MineralHandler extends DefaultHandler {
    @Getter
    private List<Mineral> mineralList;

    Mineral mineral;

    private boolean isTitle =false;
    private boolean isIngredient =false;
    private boolean isposx =false;
    private boolean isposy =false;
    private boolean isWaterBase =false;
    private boolean isWaterType =false;

    @Override
    public void startDocument() throws SAXException {
        mineralList=new ArrayList<Mineral>();
    }

    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        if(tag.equals("mineral")){
            mineral=new Mineral();
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
            mineral.setTitle(content);
        }
        if(isIngredient){
            mineral.setIngredient(content);
        }
        if(isposx){
            mineral.setPosx(Double.parseDouble(content));
        }
        if(isposy){
            mineral.setPosy(Double.parseDouble(content));
        }
        if(isWaterBase){
            mineral.setWaterBase(content);
        }
        if(isWaterType){
            mineral.setWaterType(content);
        }

    }

    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        if(tag.equals("mineral")){
            mineralList.add(mineral);
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
