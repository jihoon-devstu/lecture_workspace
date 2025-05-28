class Bullet extends GameObject{

    constructor(container,src, x, y, width, height, velX, velY){
        super(container,src, x, y, width, height, velX, velY);
    }

    //제거 메서드
    removeObject(array, target){
        //1) 화면에서 제거
        //this 나의 인스턴스를 가리키는 대명사, 총알 입장에선 "나"
        let obj= array[array.indexOf(target)];
        this.container.removeChild(obj.img);
        //2) 총알이 있던 배열에서도 제거 (제거 안하면 반복문이 계속 총알이 있는 줄 안다)
        array.splice(array.indexOf(target),1);
    }

    tick(){
        this.x+=this.velX;
    }

    render(){
        //총알이 한걸음씩 나아갈 때 마다,  총알과 적군과의 충돌을 체크해서 제거처리
        for(let i=0; i<enemyArray.length;i++){
            let enemy = enemyArray[i]; //적군 한마리 꺼내기

            if(collisionCheck(this.img,enemy.img)){ //충돌했다면
                this.removeObject(enemyArray,enemy);
                this.removeObject(bulletArray,this);
                setScore(10); //10점 증가
            }
        }
        

        if(this.x > 1400){
            this.removeObject(bulletArray,this);
        }
        this.img.style.left=this.x+"px";
    }
}