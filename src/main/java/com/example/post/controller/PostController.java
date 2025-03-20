package com.example.post.controller;

import com.example.post.dto.*;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostSaveResponseDto> save(@RequestBody PostSaveRequestDto dto) {
        return ResponseEntity.ok(postService.save(dto));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> findOne(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findById(postId));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostUpdateResponseDto> update(@PathVariable Long postId, @RequestBody PostUpdateRequestDto dto) {
        return ResponseEntity.ok(postService.update(postId, dto));
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.deleteById(postId);
    }
}

