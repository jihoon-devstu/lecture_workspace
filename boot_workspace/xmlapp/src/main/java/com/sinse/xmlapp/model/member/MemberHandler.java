package com.sinse.xmlapp.model.member;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MemberHandler extends DefaultHandler{
    @Getter
    private List<Member> memberList;

    Member member;
    //실행부가 어느 태그를 지나가는지를 알 수 있는 기준 변수
    private boolean isName=false;
    private boolean isAge=false;
    private boolean isJob=false;
    private boolean isTel=false;

    //문서가 시작될 때
    @Override
    public void startDocument() throws SAXException {
        memberList = new ArrayList<Member>();
    }

    //시작 태그를 만났을 때
    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        //member를 만나면 Model 올리기
        if(tag.equals("member")){
            member = new Member();
        }else if (tag.equals("name")){
            isName=true;
        }else if (tag.equals("age")){
            isAge=true;
        }else if (tag.equals("job")){
            isJob=true;
        }else if (tag.equals("tel")){
            isTel=true;
        }
    }


    //태그와 태그 사이의 컨텐츠를 만났을 때
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch,start,length);
        if(isName)member.setName(content);
        if(isAge)member.setAge(Integer.parseInt(content));
        if(isJob)member.setJob(content);
        if(isTel)member.setTel(content);

    }


    //종료 태그를 만났을 때
    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {

        if(tag.equals("member")){
            //한 사람의 정보가 완료 되었으므로 , List에 추가
            memberList.add(member);
        }else if (tag.equals("name")){
            isName=false;
        }else if (tag.equals("age")){
            isAge=false;
        }else if (tag.equals("job")){
            isJob=false;
        }else if (tag.equals("tel")){
            isTel=false;
        }
    }


    //문서가 끝날 때
    @Override
    public void endDocument() throws SAXException {
        log.debug("xml 문서 파싱 후 담겨진 회원수는 " +memberList.size());
    }
}
