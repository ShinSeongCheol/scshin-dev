package kr.scshin.scshin_dev.blog.adapter.in.web;

import kr.scshin.scshin_dev.blog.adapter.in.web.dto.response.PostResponse;
import kr.scshin.scshin_dev.blog.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BlogController {

    private final PostReadUseCase postReadUseCase;

    @GetMapping("/blog")
    public String blog(Model model) {
        List<PostReadResponse> readPostList = postReadUseCase.readPostList();
        log.info("readPostList: {}", readPostList.toString());

        List<PostResponse> postResponseList = readPostList.stream().map(post -> {
            String thumbnailUrl = post.imageUrls().isEmpty() ? null : post.imageUrls().get(0);
            return PostResponse.builder()
                    .title(post.title())
                    .content(post.content())
                    .createdAt(post.createdAt())
                    .thumbnailUrl(thumbnailUrl)
                    .build();
        }).toList();
        model.addAttribute("postResponseList", postResponseList);
        return "blog/index";
    }
}
