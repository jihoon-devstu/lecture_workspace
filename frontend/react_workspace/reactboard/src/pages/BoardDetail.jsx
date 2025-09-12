import {Link,useNavigate} from "react-router-dom"
export default function BoardDetail(){
    const navigate = useNavigate();
    return (
        <>
            <div>
                <h2>{'오늘 점심은 피자'}</h2>
                <p>작성자 :{'수퍼맨'}</p>
                <p>작성일 :{'2025-09-12'}</p>
                <p>조회수 :{'28'}</p>
                <Link to={''}>수정</Link>
                <button type="button" onClick={()=>navigate("/")}>삭제</button>
                <Link to={'/'}>목록</Link>
            </div>
        </>
    )
}