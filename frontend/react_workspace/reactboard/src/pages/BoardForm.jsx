import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { insertBoard, updateBoard } from "../api/api";
export default function BoardForm(){
    const isEdit = false;
    const navigate = useNavigate();

    //이 컴포넌트에서 관리되어질 상태값들...
    const [title,setTitle] = useState('');
    const [writer,setWriter] = useState('');
    const [content,setContent] = useState('');


    //함수 정의
    const saveBoard = async() =>{
        const data={title,writer,content};
        if(isEdit==false){
            //글 쓰기
            await insertBoard(data);
        }else{
            //글 수정
            await updateBoard(data);
        }

        //쓰기/수정이 성공되면 목록으로 가자
        navigate("/");

    }
    return(
        <>
            <div>
                <h2>{isEdit ? '글 수정':'글 새로 작성'}</h2>
                <form action="">
                    <div>
                        <input type="text" placeholder="제목 입력" value={title}/>
                    </div>
                    <div>
                        <input type="text" placeholder="작성자 입력" value={writer}/>
                    </div>
                    <div>
                        <textarea placeholder="내용 입력" value={content}></textarea>
                    </div>
                    <button type="button">
                        {isEdit?'수정하기':'등록하기'}
                    </button>
                    <button type="button" onClick={()=>navigate("/")}>목록보기</button>
                </form>
            </div>
        </>
    )
}