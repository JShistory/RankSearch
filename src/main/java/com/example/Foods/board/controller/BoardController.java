package com.example.Foods.board.controller;

import com.example.Foods.board.entity.Board;
import com.example.Foods.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("")
    public String showBoard(Model model){
        model.addAttribute("boardList",boardService.findAll());
        return "board/boardList";
    }

    @GetMapping("/write")
    public String showWriteBoard(){
        return "board/boardWrite";
    }

    @PostMapping("/save")
    public String writeBoard(@ModelAttribute("title") String title, @ModelAttribute("content") String content){
        Board board = new Board();
        board.setContent(content);
        board.setTitle(title);
        boardService.write(board);
        return "redirect:/board";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id){
        Board boardId = boardService.findById(id);
        boardService.delete(boardId);
        return "redirect:/board";
    }
}
