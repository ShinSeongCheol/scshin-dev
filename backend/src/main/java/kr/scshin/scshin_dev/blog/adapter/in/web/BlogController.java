package kr.scshin.scshin_dev.blog.adapter.in.web;

import kr.scshin.scshin_dev.blog.adapter.in.web.dto.response.PostDetailResponse;
import kr.scshin.scshin_dev.blog.adapter.in.web.dto.response.PostResponse;
import kr.scshin.scshin_dev.blog.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BlogController {

    private final PostReadUseCase postReadUseCase;

    @GetMapping("/posts")
    public List<PostResponse> getPosts() {
        List<PostReadResponse> readPostList = postReadUseCase.readPostList();
        log.info("readPostList: {}", readPostList.toString());

        return readPostList.stream().map(post -> {
            String thumbnailUrl = post.imageUrls().isEmpty() ? null : post.imageUrls().get(0);
            return PostResponse.builder()
                    .id(post.id())
                    .title(post.title())
                    .content(post.content())
                    .createdAt(post.createdAt())
                    .thumbnailUrl(thumbnailUrl)
                    .build();
        }).toList();
    }

    @GetMapping("/{post_id}")
    public String postDetail(Model model, @PathVariable("post_id") Long post_id) {
        PostReadQuery postReadQuery = PostReadQuery.builder().postId(post_id).build();
        PostReadResponse postReadResponse = postReadUseCase.readPostAsHtml(postReadQuery);
        PostDetailResponse postDetailResponse = PostDetailResponse.builder()
                .title(postReadResponse.title())
                .content(postReadResponse.content())
                .cratedAt(postReadResponse.createdAt())
                .updatedAt(postReadResponse.updatedAt())
                .build();

        model.addAttribute("post", postDetailResponse);
        return "blog/post_detail";
    }
}
