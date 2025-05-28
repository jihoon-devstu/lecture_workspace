    let setX; //Target의 x값 
    let setY; //Target의 y값
    let targetX= []; //Target.x를 랜덤하게 출력하기 위해서 선언한 배열
    let targetY= []; //Target.y를 랜덤하게 출력하기 위해서 선언한 배열

    let num = 1; //누르는 번호와 , 타겟 번호가 일치할 때의 조건을 주기위한 변수

    let min = 0;  //stopwatch의 분 변수
    let sec = 0;  //stopwatch의 초 변수
    let sec_dec = -1; //stopwatch의 0.단위 초 변수 (처음 호출되므로 -1)
    let mm; //stopwatch의 분 표시에 필요한 변수
    let ss; //stopwatch의 초 표시에 필요한 변수
    let sss; //stopwatch의 0.단위 초 표시에 필요한 변수

    let level = 1; //level의 디폴트값을 1단계로 주기위한 변수, 2~5단계 설정

    let game=true; //타겟의 클릭이벤트가 일시중지되었을 때 막히도록 하기 위한 변수
    let gameStarted = false; //게임 시작과 종료를 제어하기 위한 변수 
    let gamePaused = false; //게임 일시중지와 재개를 제어하기 위한 변수 
    let intervalID; //setInterval의 상태를 조절하기 위한 변수
    let gamelevel = false;
    let printhow = false;
    let resetbutton = false;
    let setRecord = false;

    //getElementByID로 활용하기위한 변수들
    let content;
    let howtowrapper;
    let howto;
    let wrap;
    let watch;
    let start;
    let reset;
    let howplay;
    let closehowto;
    let selectLevel;
    let gameResult;
    let gameResultWrapper;
    let recordClose;
    let record;

    //게임이 끝날 때, 게임 기록을 누적시키기 위한 변수
    let gameCount = 1;


    // 게임 시작 눌렀을 때 , 글씨 빨간색으로 바꾼 후 , 3초 뒤에 검은색으로 바꿔주는 함수.
    // gamestart에서 3,2,1 출력되게 활용
    function countDown(){
        
        let watchWrapper = document.getElementById("watchWrapper");
 
        content.appendChild(watch);
        watch.style.position="absolute";
        watch.style.width=500+"px";
        watch.style.height=200+"px";
        watch.style.lineHeight=200+"px";
        watch.style.textAlign="center";
        watch.style.fontSize=60+"px";
        watch.style.backgroundColor="lightcoral";
        watch.style.left=150+"px";
        watch.style.top=260+"px";
        
        setTimeout(()=>{
            watch.innerText=`00:03:00`
        },1);
        setTimeout(()=>{
            watch.innerText=`00:02:00`
        },1000);
        setTimeout(()=>{
            watch.innerText=`00:01:00`
        },2000);

        setTimeout(()=>{
            watchWrapper.appendChild(watch);
            watch.style.position="static";
            watch.style.width=150+"px";
            watch.style.height=100+"px";
            watch.style.fontSize=30+"px";
            watch.style.lineHeight=100+"px";
            watch.style.textAlign="center";
            watch.style.backgroundColor="lightgreen";
        },3000)
        }


    //게임의 stopwatch를 셋팅하는 함수
    function setStopWatch() {
        sec_dec++;

        if (sec_dec >= 100) {
            sec_dec = 0;
            sec++;
        }
        if (sec >= 60) {
            sec = 0;
            min++;
        }
        mm = min < 10 ? '0' + min : '' + min;
        ss = sec < 10 ? '0' + sec : '' + sec;
        sss = sec_dec < 10 ? '0' + sec_dec : '' + sec_dec;

        watch.innerText=`${mm}:${ss}:${sss}`;
    }


    //게임 시작 , 일시중지 , 재개를 담당하는 함수
    function gameStart(){
        
        let choose = document.getElementById("level");
        
        start.addEventListener("click",function(){
            if(!gameStarted){
                closeHowPlay();
                closeRecord();
                
                if (gamelevel == true){
                    gamelevel=false;
                    levelWrapper.appendChild(wrap);
                    wrap.style.display="none";
                }

                //게임 시작 버튼 눌림
                gameStarted = true;
                //게임 일시 정지 버튼을 3초뒤에 활성화 하게 하기 위해서 잠시 비활성화.
                gamePaused = false;

                //게임 시작 누를 때 , 시작 버튼 잠시 비활성화
                start.disabled = true;
                //게임 시작 누를 때 , 난이도 선택 버튼 비활성화
                choose.disabled = true;
                
                //3초 이후 버튼 활성화 및 버튼 innerText 변경                
                setTimeout(function(){
                    start.disabled=false;
                    start.innerText="일시 정지";
                    reset.disabled=false;
                    resetbutton=true;
                },3000)

                Gamestart_reset();
                countDown();

                setTimeout(function(){
                    intervalID = setInterval(setStopWatch,10);},
                    3000);

            }else if(gamePaused == false){
                //일시정지
                game=false;
                gamePaused=true;
                clearInterval(intervalID);
                start.innerText="게임 재개";

            }else{
                //일시정지 해제
                game=true;
                gamePaused = false;
                intervalID = setInterval(setStopWatch,10);
                start.innerText="일시 정지";
            }                               
        })
    }

    //gameover 됐을 때 게임을 target을 다 삭제 후 모든 조건을 초기화시키는 함수
    function Gamestart_reset(){
        let targets = document.querySelectorAll("#content div");
        targets.forEach(function(el){
            el.remove();             
        });
        
        num = 1;
        min = 0;
        sec = 0;
        sec_dec = -1;
        watch.innerText= "00:00:00";

        setTimeout(function(){
            createTarget();
        },3000);
    }

    //게임 종료시 , 아래 직전 게임의 기록을 보여주는 함수
    function footerResult(){
        let footerprint = (mm > 0) ? `${mm} 분 ${ss} 초 ${sss}` : `${ss} 초 ${sss}`;
        document.getElementById("footer").innerText=`직전기록 : ${level} 단계 _ ${footerprint}`
    }

    //게임 종료 시 , 판수 누적 및 게임 기록을 누적시켜서 기록하는 함수
    function printRecord(){
 
        let c_round = document.getElementById("c_round");
        let r_newRecord = `${gameCount}회차<br>` 
        c_round.innerHTML += r_newRecord;

        let c_level = document.getElementById("c_level");
        let l_newRecord = `${level}단계<br>` 
        c_level.innerHTML += l_newRecord;

        let c_time = document.getElementById("c_time");

        let t_newRecord = (mm > 0) ? `${mm} 분 ${ss} 초 ${sss}<br>` : `${ss} 초 ${sss}<br>`;

        c_time.innerHTML += t_newRecord;

        gameCount++;
    }

    //이전 기록 결과창을 불러왔다가 , 사라지게 하는 함수
    function moveRecord(){
        if(setRecord==false){
            setRecord=true;
            gameResult.style.display="block";
            content.appendChild(gameResult);
        }else{
            setRecord=false;
            gameResult.style.display="none";
            gameResultWrapper.appendChild(gameResult);
        }
    }

    //content에 부착된 target이 다 사라지면, 걸린 시간초 표시 후에 게임을 재시작 할 수 있도록 리셋하는 함수
    function gameOver(){
        if(document.querySelectorAll("#content div").length === 0){
            clearInterval(intervalID); //인터벌 제거
            moveRecord();
            printRecord();

            footerResult(); //아랫단에 직전 기록 갱신

            gameStarted = false; //게임 시작 조건 초기화
            gamelevel = false; //난이도 설정 조건 초기화

            watch.innerText="00:00:00"; //스탑워치 초기화
            start.innerText="게임 시작"; //게임시작버튼 초기화

            wrap.style.display = "none"; //wrap에 있던 난이도 설정 버튼 비활성화

            //게임이 종료되었을 때 , 난이도 선택 버튼 재활성화
            document.getElementById("level").disabled=false;
            resetbutton = false;
            reset.disabled=true;
        }
    }

    //게임 초기화 버튼을 눌렀을 때 , 게임 모든 조건 초기화

    function resetGame(){
        closeHowPlay();
        closeRecord(); 
        if(resetbutton==true){
            let targets = document.querySelectorAll("#content div");
            targets.forEach(function(el){
                el.remove();             
            });
            
            clearInterval(intervalID); //인터벌 제거

            gameStarted = false; //게임 시작 조건 초기화
            gamelevel = false; //난이도 설정 조건 초기화
            resetbutton = false; //리셋버튼을 다시 누를 수 없게 상태 바꿈

            watch.innerText="00:00:00"; //스탑워치 초기화
            start.innerText="게임 시작"; //게임시작버튼 초기화

            wrap.style.display = "none"; //wrap에 있던 난이도 설정 버튼 비활성화

            //게임이 종료되었을 때 , 난이도 선택 버튼 재활성화
            document.getElementById("level").disabled=false;
            
            reset.disabled=true;
        }
    }
    
    
    //target의 x값과 y값을 랜덤하게 주기 위해 배열에 push하여 준비하는 함수
    function prepareXY(){
        for(x=30;x<700;x+=20){
            targetX.push(x);
        }

        for(y=30;y<700;y+=20){
            targetY.push(y);
        }
    }
    
    //target의 x값과 y값을 정해진 구역 내에서 랜덤하게 뿌려주는 변수
    function setTargetXY(){
        setX = targetX[parseInt(Math.random()*34)];
        setY = targetY[parseInt(Math.random()*32)];
    }
    
    //1~30까지 target의 xy를 랜덤하게 주며 Target 인스턴스를 level값만큼 생성
    //30부터 1까지 역순으로 생성해야 아래숫자가 위 숫자에 가려지는 경우가 없음.
    function createTarget(){            
        for(let i=(level*10);i>0;i--){
            setTargetXY();
            new Target(document.getElementById("content"),setX,setY,i);              
        }
    }

    //난이도 설정 (1단계 : 1~10 , 2단계 : 1~20 ~~~ 5단계 : 1~50)
    function setLevel(){
        let wrap = document.getElementById("wrap");
        if(gamelevel==false){
            gamelevel=true;
            content.appendChild(wrap);
            wrap.style.display="block";
            wrap.style.left=18+"%";
            wrap.style.top=10+"%";
        }else{gamelevel=false;
            levelWrapper.appendChild(wrap);
            wrap.style.display="none";
        }   
    }

    function printHowPlay(){
        if(printhow==false){
            printhow=true;
            howto.style.display="block";
            content.appendChild(howto);
        }else{
            printhow=false;
            howto.style.display="none";
            howtowrapper.appendChild(howto);
        }
    }

    //innerHTML 에 스페이스바를 담당하는 함수.
    function spacebar(n) {
        return "&nbsp;".repeat(n);
    }

    //게임 설명 p태그 안에 HTML 을 삽입하는 함수. (로드될 때 init 에서 호출) 
    //body단에서 아래 문구를 다 적으면 너무 지저분해 지므로 스크립트단에서 제어.
    function setHowplay(){
        let h1 = document.querySelector("#howto > :nth-child(2)")
        h1.innerHTML=`① 1~5단계의 난이도를 설정후, <span style="font-weight:bold;font-size: 23px;">게임시작</span> 버튼을 누르면 , 3초 뒤 게임이 시작됩니다.`;

        let h2 = document.querySelector("#howto > :nth-child(3)")
        h2.innerHTML=`② 게임 시작 후 , 1부터 순서대로 <span style="color: skyblue; font-weight: bold;font-size: 23px;">하늘색</span> 타겟을 클릭하세요.`;

        let h3 = document.querySelector("#howto > :nth-child(4)")
        h3.innerHTML=`③ 순서에 맞는 숫자를 클릭하면 타겟이 사라지고,<br>${spacebar(5)}틀리면 <span style="color: red; font-weight: bold;font-size: 23px;">빨간색</span> 으로 잠깐 표시됩니다.`;

        let h4 = document.querySelector("#howto > :nth-child(5)")
        h4.innerHTML=`④ 게임 도중에, 게임 시작 버튼 위치에 있던 <span style="font-weight:bold;font-size: 23px;">일시정지</span>버튼으로 <br>${spacebar(5)} 게임을 잠시 멈출 수 있습니다.  `;

        let h5 = document.querySelector("#howto > :nth-child(6)")
        h5.innerHTML=`⑤ <span style="font-weight:bold;font-size: 23px;">게임 초기화</span> 버튼을 통해 난이도 선택 화면으로 <br>${spacebar(5)}돌아갈 수 있습니다.`;

        let h6 = document.querySelector("#howto > :nth-child(7)")
        h6.innerHTML=`⑥ <span style="font-weight:bold;font-size: 23px;">마지막 타겟</span>을 클릭하면 게임이 종료되며 <br>${spacebar(5)} 직전 게임의 기록이 화면 아래 표기됩니다.`;

        let h7 = document.querySelector("#howto > :nth-child(8)")
        h7.innerHTML=`⑦ <span style="font-weight:bold;font-size: 23px;">이전 기록</span> 버튼을 누르면 플레이했던 게임의 기록을 확인할 수 있습니다.<br>${spacebar(5)} 단, 도중에 초기화한 게임은 기록되지 않습니다.`;
        
        let h8 = document.querySelector("#howto > :nth-child(9)")
        h8.innerHTML=`⑧ 게임 종료 후 , 따로 난이도 설정을 하지 않으면 게임 시작 시 , <span style="color:blue; font-weight:bold;font-size: 23px;"><br>${spacebar(5)}직전 단계</span>의 난이도로 설정됩니다.`;

    }
    
    //게임이 종료되거나 , 게임 초기화시 게임 설명이 el.remove에 걸리지 않게 하기 위해 만든 함수.
    function closeHowPlay(){
        if(printhow==true){
            howto.style.display="none";
            howtowrapper.appendChild(howto);
            printhow = false;
        }                
    }

    //게임이 종료되거나 , 게임 초기화시 이전 기록이 el.remove에 걸리지 않게 하기 위해 만든 함수.
    function closeRecord(){
        if(setRecord==true){
            setRecord=false;
            gameResult.style.display="none";
            gameResultWrapper.appendChild(gameResult);
        }
    }
    //게임이 종료되거나 , 게임 초기화시 난이도 선택이 el.remove에 걸리지 않게 하기 위해 만든 함수.
    function closeLevel(){
        if(gamelevel==true){
            gamelevel=false;
            wrap.style.display="none";
            levelWrapper.appendChild(wrap);
        }
    }

    function ClickLevel(levnum){

        level = levnum;

        let levelWrapper = document.getElementById("levelWrapper");
        let wrap =document.getElementById("wrap");
        let levelprint = document.getElementById("levelprint");
        
        gamelevel=false;
        start.disabled=false;
        wrap.style.display="none";
        levelWrapper.appendChild(wrap);
        levelprint.innerText=`${levnum}단계`;

    }

    //onload시 준비되어야하는 함수 
    function init(){
        content = document.getElementById("content");
        howtowrapper = document.getElementById("howtowrapper");
        howto = document.getElementById("howto");
        wrap = document.getElementById("wrap");
        watch=document.getElementById("watch");
        start = document.getElementById("start");
        reset = document.getElementById("reset");
        howplay = document.getElementById("howPlay");
        closehowto = document.getElementById("closehowto");
        selectLevel = document.getElementById("level");
        gameResult = document.getElementById("gameResult");
        gameResultWrapper = document.getElementById("gameResultWrapper");
        recordClose = document.getElementById("recordClose");
        record = document.getElementById("record");

        prepareXY();
        gameStart();
        setLevel();
        setHowplay();
    }

    //로드시점에 주는 클릭 이벤트들
    addEventListener("load",function(){
        init();
        start.disabled=true;
        reset.disabled=true; 
        
        howplay.addEventListener("click",()=>{
            closeRecord();
            closeLevel();
            printHowPlay();
        })
        
        selectLevel.addEventListener("click",()=>{
            closeRecord();
            closeHowPlay();
            setLevel();
        })

        record.addEventListener("click",()=>{
            closeHowPlay();
            closeLevel();
            moveRecord();
        })

        closehowto.addEventListener("click",()=>{
            closeHowPlay();                
        })

        recordClose.addEventListener("click",()=>{
            closeRecord();
        })
        reset.addEventListener("click",()=>{
            resetGame();               
        })

        for(let i=1;i<=8;i++){
            document.getElementById(`lev${i}`).addEventListener("click",()=>{
                    ClickLevel(i);
                });
        }     
    })