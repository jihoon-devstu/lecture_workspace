/*--------------------------------------------------------------
  랜덤한 값 구하기
---------------------------------------------------------------*/
//원하는 정수를 반환받기 위해서는 n값을 호출시 결정하자.
// 예) getRandom(11)을 넘기면 0 ~ 10 반환
// 예) getRandom(10)을 넘기면 0 ~ 9 반환
function getRandom(max){
    return parseInt(Math.random()*(max+1)); //0~4
}

/*--------------------------------------------------------------
  범위를 지정한 랜덤한 값 구하기
  getRandomByRange(5,8) 5~8사이의 랜던값 반환
---------------------------------------------------------------*/
function getRandomByRange(min , max){
    return min+ (parseInt(Math.random()*((max-min)+1)));
}

/*---------------------------------------------------------------
한 자리수 정수에 대한 0처리
---------------------------------------------------------------*/

function zeroString(n){
    //만일 n이 한자리수이면 앞에 '0' 문자를 붙여주자
    let str=n;
    if(n>0 && n<10){return "0"+str;}
}

/*--------------------------------------------------------------
해당 월의 시작 요일 구하기
API 사용 예) 2025년 5월 getStartDay(2025,4)
---------------------------------------------------------------*/
function getStartDay(yy,mm){

    let d = new Date(yy, mm,1);
    return d.getDay();
}

/*--------------------------------------------------------------
영어 또는 한국어 요일을 변환하여 반환하기
API 사용 예) convertDay(2,"eng");
---------------------------------------------------------------*/
function convertDay(n,lang){
    let korArray =["일요일","월요일","화요일","수요일","목요일","금요일","토요일"];
    let engArray =["Sun","Mon","Tue","Wed","Tur","Fri","Sat"];

    let day = (lang=="kor")? korArray[n] : engArray[n]; //어떤 요일을 선택할지 결정
    return day;
}

/*--------------------------------------------------------------
해당 월의 마지막 날 구하기
API 사용 예) getLastDay(원하는연도,원하는 월)
---------------------------------------------------------------*/

function getLastDate(yy,mm){

    let d = new Date(yy, mm+1,0);
    return d.getDate();
}

/*--------------------------------------------------------------
충돌 체크 함수
---------------------------------------------------------------*/

function collisionCheck(me , target){
    //나에대한 수치 계산
    let me_x = parseInt(me.style.left);
    let me_y = parseInt(me.style.top);
    let me_width = parseInt(me.style.width);
    let me_height = parseInt(me.style.height);

    let target_x = parseInt(target.style.left);
    let target_y = parseInt(target.style.top);
    let target_width = parseInt(target.style.width);
    let target_height = parseInt(target.style.height);

    return !(
    me_x + me_width < target_x || //me의 오른쪽이 타겟의 왼쪽보다 왼쪽에 있으면.. 
    me_x > target_x + target_width || //me의 왼쪽이 타겟의 우측보다 더 오른쪽에 있으면..
    me_y + me_height < target_y || //me의 아랫쪽이 타겟보다 위에 있으면..
    me_y > target_y + target_height //me의 윗쪽이 타겟의 아래보다 아래에 있으면
    )
}