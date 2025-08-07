package com.sinse.restapp.controller;

import com.sinse.restapp.domain.Board;
import com.sinse.restapp.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BoardController {

    private BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/test")
    public String test(){
        return "Jihoon";
    }
    //게시판 목록 요청 처리
    @GetMapping("/boards")
    public List selectAll(){
        List list = boardService.selectAll();
        return list;
    }

    //글쓰기 요청
    @PostMapping("/boards")
    //JSON 문자열로 전송된 파라미터와 서버측의 모델과의 자동 매핑이 RequestBody에서 일어남. (Spring MVC 부터 있었음.)
    public ResponseEntity<String> regist(@RequestBody Board board){
        boardService.insert(board);
        return ResponseEntity.ok("success");
    }

    //결국 아래의 url에 대해 Restful 한 URL을 이해하고 있음.
    @GetMapping("/boards/{board_id}")
    public Board select(@PathVariable int board_id){
        Board board = boardService.select(board_id);
        return board;
    }


    @PutMapping("/boards/{board_id}")
    public ResponseEntity<String> update(@PathVariable int board_id, @RequestBody Board board){
        board.setBoard_id(board_id); //경로로 전송 된 파라미터를 다시한번 확인 차 모델에 대입.
        boardService.update(board);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("boards/{board_id}")
    public ResponseEntity<String> delete(@PathVariable int board_id){
        boardService.delete(board_id);
        return ResponseEntity.ok("success");
    }


}














