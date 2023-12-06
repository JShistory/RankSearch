package com.example.Foods.board.service;

import com.example.Foods.board.entity.Board;
import com.example.Foods.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long delete(Board board){
        boardRepository.delete(board);
        return board.getId();
    }

    @Transactional
    public Long write(Board board){
        boardRepository.save(board);
        return board.getId();
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public Board findById(Long id){
        return boardRepository.findById(id).get();
    }

    @Transactional
    public void update(Long id, String content, String title){
        Board board = boardRepository.findById(id).get();
        if(title == null){
            title = board.getTitle();
        }
        if(content == null)
            content = board.getContent();
        board.setTitle(title);
        board.setContent(content);
    }

}
