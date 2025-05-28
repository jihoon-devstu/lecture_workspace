class Brick extends GameObject{
    constructor(container,x,y,width,height,velX,velY,bg,border,borderColor){
        //js는 java처럼 super() 자동으로 호출해주는 기능은 없음.
        //개발자가 무조건 직접 나서야함.
        super(container,x,y,width,height,velX,velY,bg,border,borderColor);
    }
}