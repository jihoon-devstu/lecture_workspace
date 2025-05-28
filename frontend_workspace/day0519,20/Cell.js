//달력의 Cell 정의
class Cell {

    constructor(container,x,y,width,height,bg,borderColor,date){

        //[다이어리 관련 내용]
        this.year;
        this.month;
        this.date=date;
        this.icon;

        //[UI 관련 내용]
        this.container=container;
        this.div=document.createElement("div"); //셀 자체 박스
        this.numDiv=document.createElement("div");; //달력 날짜 출력
        this.titleDiv=document.createElement("div");; //다이어리 제목
        this.iconDiv=document.createElement("div");; //아이콘이 위치할 영역
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.bg=bg;
        this.borderColor=borderColor;

        
        //cell div style
        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";

        this.div.style.width=this.width+"px";
        this.div.style.height=this.height+"px";

        this.div.style.backgroundColor=this.bg;
        this.div.style.border="2px solid " + this.borderColor;
        this.div.style.boxSizing="border-box"; //border,margin,padding에 의한 박스의 크기가 
                                                // outside 쪽으로 커지지 않고 , 안쪽으로 커짐.

        //cell numDiv style
        this.numDiv.style.width=100+"%";
        this.numDiv.style.height=25+"px";
        this.numDiv.style.textAlign="right";
        this.numDiv.style.padding="0px 5px 0px 0px"; //top right bottom right
        this.numDiv.style.boxSizing="border-box";
        

        //cell titleDiv style
        this.titleDiv.style.width=100+"%";
        this.titleDiv.style.height=25+"px";

        //cell iconDiv style
        this.iconDiv.style.width=100+"%";
        this.iconDiv.style.height=50+"px";

        //셀에 3층 div 각각 부착
        this.div.appendChild(this.numDiv);
        this.div.appendChild(this.titleDiv);
        this.div.appendChild(this.iconDiv);

        //셀 컨테이너에 부착
        this.container.appendChild(this.div);

        //현재 이 셀에 클릭 이벤트 구현

        this.div.addEventListener("click",()=>{
            //창을 띄우기
            //클릭한 셀의 -> diaryarrey를 조사해봤을 때 -> 값이 있으면 -> 해당 값을 불러오기 / 아니면 오픈 다이얼로그
            /*if(){

            }else{*/
                openDialog(this)
            /*}
            ;
            */
        })

    }

    //셀에 보여질 날짜를 수시로 변경해야 하므로, 메서드의 대상이 될 수 있음.
    // 
    setDate(year,month,date){
        this.year=year;
        this.month=month;
        this.date=date;

        //랜더링 처리
        this.numDiv.innerText=date;
    }

    renderIcon(src, width){ //어떤 이미지를 원하는지는 호출자가 결정
        this.icon=document.createElement("img");
        this.icon.src=src;
        this.iconDiv.appendChild(this.icon);
        this.icon.style.width=width+"px";
    }
}