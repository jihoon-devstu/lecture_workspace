class GameObject{
    constructor(container,x,y,width,height,velX,velY,bg,border,borderColor){
        this.container=container;
        //게임 완성 후 , div를 버리지 말고, 그대로 유지하되 이미지를 배경처리
        //이미지로 개발하지 못하는 이유? 이미지는 부모 기능이 없어 , 남을 포함할 수 없음
        this.div=document.createElement("div"); 
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.velX=velX;
        this.velY=velY;
        this.bg=bg;
        this.border=border;
        this.borderColor=borderColor;

        //style 및 조립
        
        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";

        this.div.style.width=this.width+"px";
        this.div.style.height=this.height+"px";

        this.div.style.backgroundColor=this.bg;
        this.div.style.border=`${this.border}px solid ${this.borderColor}`;

        this.container.appendChild(this.div);
    }
}