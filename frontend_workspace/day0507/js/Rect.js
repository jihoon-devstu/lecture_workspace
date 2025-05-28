/*현실의 사각형 막대를 정의한다*/ 

class Rect{
    constructor(container, x, y, width, height, bg){
        this.container=container;
        this.div=document.createElement("div");
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.bg=bg;
        this.targetA=400;
        this.a=0.1;


        //style

        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";

        this.div.style.width=this.width+"px";
        this.div.style.height=this.height+"px";

        this.div.style.background=this.bg;

        //부모에 부착
        this.container.appendChild(this.div);

        this.move(); //태어나자 마자, 나의 루프를 호출
        
    }

    move(){
        console.log("move()...")

        //막대의 크기를 감속도 공식을 적용하여 움직여 보자
        setTimeout(()=>{
            this.div.style.height=this.div.style.height+this.a*(this.targetA-this.div.style.height);
            this.move();
            
        },10);
    }


}