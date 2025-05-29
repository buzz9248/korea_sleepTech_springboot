package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.constants.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.request.PostCreateReqDto;
import com.example.korea_sleepTech_springboot.dto.request.PostUpdateReqDto;
import com.example.korea_sleepTech_springboot.dto.response.*;
import com.example.korea_sleepTech_springboot.entity.D_Post;
import com.example.korea_sleepTech_springboot.repository.PostRepository;
import com.example.korea_sleepTech_springboot.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public ResponseDto<PostDetailRespDto> createPost(PostCreateReqDto dto) {
        PostDetailRespDto respDto = null;

        D_Post newPost = D_Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .build();

        D_Post saved = postRepository.save(newPost);

        respDto = PostDetailRespDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .author(saved.getAuthor())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<PostDetailRespDto> getPostById(Long id) {
        PostDetailRespDto respDto = null;

        D_Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        List<CommentRespDto> comments = post.getComments().stream()
                .map(comment -> CommentRespDto.builder()
                        .id(comment.getId())
                        .postId(comment.getPost().getId())
                        .content(comment.getContent())
                        .commenter(comment.getCommenter())
                        .build())
                .collect(Collectors.toList());

        respDto = PostDetailRespDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .comments(comments) // comment 테이블에서 데이터를 찾아 CommentRespDto 형식으로 변환
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostListRespDto>> getAllPosts() {
        List<PostListRespDto> respDtos = null;

        List<D_Post> posts = postRepository.findAll();

        respDtos = posts.stream()
                .map(post -> PostListRespDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getAuthor())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDtos);
    }

    @Override
    @Transactional
    public ResponseDto<PostDetailRespDto> updatePost(Long id, PostUpdateReqDto dto) {
        PostDetailRespDto respDto = null;

        D_Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        D_Post updatedPost = postRepository.save(post);

        respDto = PostDetailRespDto.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .author(updatedPost.getAuthor())
                .comments(updatedPost.getComments().stream()
                        .map(comment -> CommentRespDto.builder()
                                .id(comment.getId())
                                .postId(comment.getPost().getId())
                                .content(comment.getContent())
                                .commenter(comment.getCommenter())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deletePost(Long id) {
//        if (!postRepository.existsById(id)) {
//            // .existsById(PK값)
//            // : 존재하면 true, 존재하지 않으면 false 반환
//            throw new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id);
//        }

        D_Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        // cf) 게시물 삭제 이전에 모든 댓글에 대해 관계 해제
        // 인스턴스 메서드 참조
        // 기본) 인스턴스명.메서드명()
        //          forEach(post -> post.removeComment());
        post.getComments().forEach(post::removeComment);


        postRepository.deleteById(id);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }


    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostListRespDto>> getPostByAuthor(String author) {
        List<PostListRespDto> respDtos = null;

        List<D_Post> posts = postRepository.findByAuthor(author);

        respDtos = posts.stream()
                .map(post -> PostListRespDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getAuthor())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDtos);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostListRespDto>> searchPostsByTitle(String keyword) {
        List<PostListRespDto> respDtos = null;

        List<D_Post> posts = postRepository.findByTitleIgnoreCaseContaining(keyword);

        respDtos = posts.stream()
                .map(post -> PostListRespDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getAuthor())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDtos);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostWithCommentCountRespDto>> getTop5PostsByComments() {
        List<PostWithCommentCountRespDto> respDtos = null;

        List<Object[]> results = postRepository.findTop5ByOrderByCommentsSizeDesc();

        respDtos = results.stream()
                .map(row -> PostWithCommentCountRespDto.builder()
                        // JPA 데이터 반환이 Object[] 타입
                        // : 내부의 데이터는 Object로 명시 >> 각 타입으로 명시적 형 변환(강제 형 변환)
                        .id(((Number) row[0]).longValue())
                        .title((String) row[1])
                        .content((String) row[2])
                        .author((String) row[3])
                        .commentCount(((Number) row[4]).intValue())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDtos);
    }
}









