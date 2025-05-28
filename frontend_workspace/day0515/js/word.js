/*단어를 정의한다 */


class Word{
    constructor(container, x, y, text, color){
        this.container=container;
        this.span=document.createElement("span");
        this.x=x;
        this.y=y;
        this.text=text; //이 span이 포함하게될 단어
        this.color=color;

        //Style & 조립
        this.span.style.display="inline-block";
        this.span.style.position="absolute";
        this.span.style.left=this.x+"px";
        this.span.style.top=this.y+"px";

        this.span.innerText=this.text;
        this.span.style.color=this.color;
        this.span.style.fontSize = "25px";
        this.span.style.fontWeight = "bold";

        this.container.appendChild(this.span);

    }

    //한발 씩 내려오기
    tick(){  //물리량 변화
        this.y+=50;
    }
    render(){ //그래픽 처리
        this.span.style.top=this.y+"px";
    }

}