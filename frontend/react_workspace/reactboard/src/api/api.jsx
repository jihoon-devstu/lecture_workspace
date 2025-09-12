/*
Vue , react , jquery ajax() 를 잘 사용하지 않는다.

[axios]
    - 비동기 방식의 요청 객체
    - web 뿐만 아니라 , node.js 와 같은 독립실행형 자바스크립트에서도 사용이 가능
    - JSON 자동 변환 지원 따라서 서버가 보내준 데이터를 바로 접근 가능 (data.boardList)
    - React , vue 프로젝트에선 axios 가 사실상 표준

[jquery ajax]
    - jquery 에서만 지원하는 웹용 비동기 요청 객체
    - 콜백 기반이라 , 코드가 복잡해질 수 있음
    - node.js 에서 못씀.
    - 서버가 보내준 정보를 sjon으로 직접 파싱
    - 기존 레거시 프로젝트에서 많이 사용해 왔으므로 , 유지 보수 시 알아야 함.
*/

import axios from 'axios';

//공통 url 선언
const URL = 'http://localhost:7777/api/boards';

//목록 보기 요청
export const getBoards = ()=> axios.get(URL); //목록 요청
export const getBoard = (boardId)=> axios.get(`${URL}/${boardId}`); //글 한 건 요청
export const insertBoard = (data)=> axios.post(URL,data); //글 쓰기 요청
export const updateBoard = (data,boardId)=> axios.put(`${URL}/${boardId}`,data); //글 수정 요청
export const deleteBoard = (boardId)=>axios.delete(`${URL}/${boardId}`); //글 삭제 요청