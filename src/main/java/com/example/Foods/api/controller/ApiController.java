package com.example.Foods.api.controller;

import com.example.Foods.board.entity.Board;
import com.example.Foods.board.service.BoardService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/boards")
    public CreatePostResponse postV1(Board board){
        Long id = boardService.write(board);


        return new CreatePostResponse(id);
    }

    @GetMapping("/api/v1/boards")
    public List<Board> boardV1(){
        List<Board> findBoards = boardService.findAll();
        return findBoards;
    }

    @PutMapping("/api/v1/boards/{id}")
    public UpdateBoardResponse UpdateBoardV1(@PathVariable("id") Long id, UpdateBoardRequest request){
        boardService.update(id,request.getContent(), request.getTitle());
        Board board = boardService.findById(id);
        return new UpdateBoardResponse(board.getId(),board.getTitle(), board.getContent());
    }

    @PatchMapping("/api/v1/boards/{id}")
    public UpdateBoardResponse UpdateBoardV2(@PathVariable("id") Long id, UpdateBoardRequest request){
        boardService.update(id,request.getContent(), request.getTitle());
        Board board = boardService.findById(id);
        return new UpdateBoardResponse(board.getId(),board.getTitle(), board.getContent());
    }

    @DeleteMapping("/api/v1/boards/{id}")
    public DeleteBoardResponse DeleteBoardV1(@PathVariable("id") Long id){
        Board board = boardService.findById(id);
        boardService.delete(board);
        return new DeleteBoardResponse(id);
    }
    @Data
    @AllArgsConstructor
    static class DeleteBoardResponse{
        private Long id;
    }

    @Data
    static class CreatePostResponse{
        private Long id;

        public CreatePostResponse(Long id){
            this.id = id;
        }
    }
    @Data
    @AllArgsConstructor
    static class UpdateBoardResponse {
        private Long id;
        private String title;
        private String content;
    }
    @Data
    static class UpdateBoardRequest {
        private String title;
        private String content;
    }
}
