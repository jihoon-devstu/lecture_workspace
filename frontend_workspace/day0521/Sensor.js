class Sensor extends GameObject{
    constructor(container, hero, x, y, width, height, bg, border, borderColor){
        super(container, hero, x, y, width, height,0,0, bg, border, borderColor);
        

        //부모의 코드에 존재하지 않는 것만 자식이 처리하면 됨..
        this.hero=hero;
    }
}