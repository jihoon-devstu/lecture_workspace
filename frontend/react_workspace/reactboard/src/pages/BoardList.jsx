import { useState } from "react";
import { useNavigate,Link } from "react-router-dom";
//나 아닌 다른 파일에서도 이 함수를 사용하도록 허용해주는 명령어
//ES6에서는 클래스뿐 아니라 , 함수를 통해서도 컴포넌트를 정의할 수 있다.
export default function BoardList(){
    /* 
        useState 는 react의 데이터 상태(state) 훅(hook) 으로
        BoardList 컴포넌트 안에서 상태 관리를 담당함.
        boards : 현재의 상태값
        setBoards : 상태를 변경할 수 있는 함수
    */
    const [boards , setBoards] = useState([1,1,1,1,1,1,1]);
    const navigate = useNavigate();
    return(
    <table>
        <thead>
            <tr>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            {boards.map(b=>(
                <tr>
                    <td><Link to={'/view'}>오늘부터 react 수업 시작</Link></td>
                    <td>길동이</td>
                    <td>2025-09-15</td>
                    <td>3</td>
                </tr>
            ))}

            <tr>
                <td colSpan={4}>
                    <button type="button" onClick={()=>navigate("/new")}>글쓰기</button>
                </td>
            </tr>
        </tbody>
    </table>
    )
}