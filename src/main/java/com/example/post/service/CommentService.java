package com.example.post.service;

import com.example.post.dto.*;
import com.example.post.entity.Comment;
import com.example.post.entity.Post;
import com.example.post.repository.CommentRepository;
import com.example.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentSaveResponseDto save(CommentSaveRequestDto dto) {
        Post post = postRepository.findById(dto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        Comment comment = new Comment(dto.getContent(), post);
        Comment savedComment = commentRepository.save(comment);
        return new CommentSaveResponseDto(savedComment.getId(), savedComment.getContent(), savedComment.getPost().getId());
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll() {
        List<Comment> comments = commentRepository.findAll();

        List<CommentResponseDto> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDto dto = new CommentResponseDto(comment.getId(), comment.getContent(), comment.getPost().getId());
            dtos.add(dto);
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 댓글이 없습니다.")
        );

        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getPost().getId());
    }

    @Transactional
    public CommentUpdateResponseDto update(Long commentId, CommentUpdateRequestDto dto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 댓글이 없습니다.")
        );

        comment.update(dto.getContent());
        return new CommentUpdateResponseDto(comment.getId(), comment.getContent());
    }

    @Transactional
    public void deleteById(Long commentId) {
        if(!commentRepository.existsById(commentId)) {
            throw new IllegalArgumentException("해당하는 댓글이 없습니다.");
        }

        commentRepository.deleteById(commentId);
    }
}
