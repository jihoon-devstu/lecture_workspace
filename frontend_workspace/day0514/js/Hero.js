class Hero extends GameObject{

    constructor(container,src, x, y, width, height, velX, velY){
        super(container,src, x, y, width, height, velX, velY);

        this.imgArray=[];
        this.n=1;

        for(let i=1;i<=18;i++){
            this.imgArray.push(`../../images/hero/image${i}.png`);
        }

        this.doidle();

    }

    doidle(){
        this.n++;
        this.img.src=this.imgArray[this.n];
        if(this.n>=17)this.n=0;
        setTimeout(()=>{
            this.doidle();
        },50);
        }

    //부모가 완성하지 못했던, 메서드를 자식이 자신의 상황에 맞게 커스텀하는 정의 기법
    //메서드 오버라이딩이라고 함.
    //부모꺼 무시하고 업그레이드 하는 행위. (overriding)

    tick(){
        this.x+=this.velX;
        this.y+=this.velY;
    }

    render(){
        this.img.style.left=this.x+"px";
        this.img.style.top=this.y+"px";
    }

}