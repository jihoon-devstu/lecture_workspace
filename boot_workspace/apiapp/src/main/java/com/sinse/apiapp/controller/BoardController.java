package com.sinse.apiapp.controller;

import com.sinse.apiapp.domain.Board;
import com.sinse.apiapp.model.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/*
* 글등록             POST           /api/boards
* 글목록             GET            /api/boards
* 상세보기          GET            /api/boards/{boardId}
* 수정하기          PUT            /api/boards/{boardId}
* 삭제하기          DELETE       /api/boards/{boardId}
* */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards")
    public List<Board> findAll(){
        return boardService.selectAll();
    }

    @GetMapping("/boards/{boardId}")
    public Board fineOne(int BoardId){
        return boardService.selectOne(BoardId);
    }


    @PostMapping("/boards")
    public ResponseEntity insert(@RequestBody Board board){
        boardService.regist(board);
        return ResponseEntity.ok(Map.of("result","등록 성공"));
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity update(@RequestBody Board board, int boardId){
        boardService.update(board);
        return ResponseEntity.ok(Map.of("result","수정 성공"));
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity delete(int boardId){
        boardService.delete(boardId);
        return ResponseEntity.ok(Map.of("result","삭제 성공"));
    }

}
