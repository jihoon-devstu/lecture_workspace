//Target div를 생성한다

class Target{

    constructor(container,x,y,number){
        this.container=container;
        this.div=document.createElement("div");
        this.x=x;
        this.y=y;
        this.number=number;
        this.clicked = false;

        this.div.style.position="absolute";
        this.div.style.margin=8+"px";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";

        this.div.style.width= 70+"px";
        this.div.style.height=70+"px";

        this.div.style.border = 3+"px solid black";
        this.div.style.backgroundColor = "lightskyblue";
        this.div.style.borderRadius = 50+"%";
        this.div.style.cursor="pointer";
        this.div.style.transition="background-color 0.2s ease";

        this.div.style.fontSize=30+"px";
        this.div.style.fontWeight="bold";
        this.div.style.textAlign="center";
        this.div.style.lineHeight=70+"px";

        this.div.innerText=number;

        //함수로 빼지 않고 , 작성해야 모든 생성되는 div들에 클릭 이벤트 부여 가능
        //clicked를 통해 각각의 div와 num의 숫자가 같을때만 클릭이벤트로 사라지도록 부여.
        //사라지기만 하면 뒤에있는 div가 겹쳐져서 눌리지 않을 수 있으므로 , 일치한다면 사라지게한후 removeChild 필수.
        //--> 유저가 누르는 숫자와 눌러야되는 div의 숫자가 일치하지 않으면 눌리지 않음.
        this.div.addEventListener("click",()=>{
            if(game==false) return;

            if(this.number===num && !this.clicked ){

                this.clicked=true;
                this.div.style.backgroundColor="#FFFFFF";        
                this.div.style.transition = 'opacity 0.2s';
                this.div.style.opacity = 0;

                setTimeout(()=>{
                    this.container.removeChild(this.div);
                    gameOver();
                }, 200);

                num++;

            }else if(this.number!=num && !this.clicked){

                this.div.style.backgroundColor="red"

                setTimeout(()=>{
                    this.div.style.backgroundColor="lightskyblue"
                },100)
                
            }
        })

        this.container.appendChild(this.div);       
    }
}