package com.example.post.controller;

import com.example.post.dto.*;
import com.example.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentSaveResponseDto> save(@RequestBody CommentSaveRequestDto dto) {
        return ResponseEntity.ok(commentService.save(dto));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> findOne(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.findById(commentId));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentUpdateResponseDto> update(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto dto) {
        return ResponseEntity.ok(commentService.update(commentId, dto));
    }

    @DeleteMapping("/comments/{commentId}")
    public void delete(@PathVariable Long commentId) {
        commentService.deleteById(commentId);
    }
}
