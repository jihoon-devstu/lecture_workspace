package com.sinse.apiapp.model.board;

import com.sinse.apiapp.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
    private final JpaBoardRepository jpaBoardRepository;

    @Override
    public List<Board> selectAll() {
        return jpaBoardRepository.findAll();
    }

    @Override
    public Board selectOne(int boardId) {
        return jpaBoardRepository.findById(boardId).orElse(null);
    }

    @Override
    public void regist(Board board) {
        jpaBoardRepository.save(board);
    }

    @Override
    public void update(Board board) {
        jpaBoardRepository.save(board);
    }

    @Override
    public void delete(int boardId) {
        jpaBoardRepository.deleteById(boardId);
    }
}
