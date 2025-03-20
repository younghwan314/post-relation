package com.example.post.service;

import com.example.post.dto.*;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostSaveResponseDto save(PostSaveRequestDto dto) {
        Post post = new Post(dto.getTitle());
        Post savedPost = postRepository.save(post);
        return new PostSaveResponseDto(savedPost.getId(), savedPost.getTitle());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostResponseDto> dtos = new ArrayList<>();
        for (Post post : posts) {
            PostResponseDto dto = new PostResponseDto(post.getId(), post.getTitle());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        return new PostResponseDto(post.getId(), post.getTitle());
    }

    @Transactional
    public PostUpdateResponseDto update(Long postId, PostUpdateRequestDto dto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        post.update(dto.getTitle());
        return new PostUpdateResponseDto(post.getId(), post.getTitle());
    }

    @Transactional
    public void deleteById(Long postId) {
        if(!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("해당하는 게시글이 없습니다.");
        }
        
        postRepository.deleteById(postId);
    }
}
