package com.sinse.xmlapp.controller;

import com.sinse.xmlapp.domain.Board;
import com.sinse.xmlapp.exception.BoardException;
import com.sinse.xmlapp.model.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class BoardController {

    private BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping("/board/list")
    public String getList(Model model){
        List boardList = boardService.selectAll();
        model.addAttribute("boardList",boardList);
        return "board/list";
    }

    @GetMapping("/board/registform")
    public String registForm(Model model){
        return "/board/write";
    }

    @GetMapping("/board/detail")
    public String getDetail(int board_id, Model model){
        Board board = boardService.select(board_id);
        model.addAttribute("board", board);
        return "board/content";
    }

    @PostMapping("/board/regist")
    public String regist(Board board){
        boardService.insert(board);
        return "redirect:/board/list";
    }

    // 수정
    @PutMapping("/board/update")
    public String update(Board board) {
        boardService.update(board);
        return "redirect:/board/detail?board_id="+board.getBoard_id();
    }

    // 삭제
    @DeleteMapping("/board/delete")
    public String delete(int board_id) {
        boardService.delete(board_id);
        return "redirect:/board/list";
    }



    @ExceptionHandler(BoardException.class)
    public ModelAndView handleException(BoardException e) {
        ModelAndView mav = new ModelAndView("error/result");
        mav.addObject("e", e);
        return mav;
    }

}
