package com.sinse.restapp.controller;

import com.sinse.restapp.domain.Board;
import com.sinse.restapp.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시판 API", description = "게시판 관련 CRUD API")
@Slf4j
@RestController
public class BoardController {

    private BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Operation(summary = "테스트 API", description = "간단한 테스트용 API")
    @GetMapping("/test")
    public String test(){
        return "Jihoon";
    }

    //게시판 목록 요청 처리
    @Operation(summary = "게시판 목록 조회", description = "모든 게시글 목록을 반환합니다.")
    @GetMapping("/boards")
    public List selectAll(){
        List list = boardService.selectAll();
        return list;
    }

    //글쓰기 요청
    @Operation(summary = "게시글 등록", description = "새 게시글을 등록합니다.")
    @PostMapping("/boards")
    //JSON 문자열로 전송된 파라미터와 서버측의 모델과의 자동 매핑이 RequestBody에서 일어남. (Spring MVC 부터 있었음.)
    public ResponseEntity<String> regist(@RequestBody Board board){
        boardService.insert(board);
        return ResponseEntity.ok("success");
    }

    //결국 아래의 url에 대해 Restful 한 URL을 이해하고 있음.
    @Operation(summary = "게시글 상세 조회", description = "특정 게시글 상세정보를 조회합니다.")
    @GetMapping("/boards/{board_id}")
    public Board select(@PathVariable int board_id){
        Board board = boardService.select(board_id);
        return board;
    }

    @Operation(summary = "게시글 수정", description = "특정 게시글을 수정합니다.")
    @PutMapping("/boards/{board_id}")
    public ResponseEntity<String> update(@PathVariable int board_id, @RequestBody Board board){
        board.setBoard_id(board_id); //경로로 전송 된 파라미터를 다시한번 확인 차 모델에 대입.
        boardService.update(board);
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제합니다.")
    @DeleteMapping("boards/{board_id}")
    public ResponseEntity<String> delete(@PathVariable int board_id){
        boardService.delete(board_id);
        return ResponseEntity.ok("success");
    }


}