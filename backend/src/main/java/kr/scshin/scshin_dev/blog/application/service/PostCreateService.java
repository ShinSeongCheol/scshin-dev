package kr.scshin.scshin_dev.blog.application.service;

import kr.scshin.scshin_dev.blog.application.port.in.PostCreateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "BlogPostCreateService")
@RequiredArgsConstructor
public class PostCreateService implements PostCreateUseCase {

    private final PostCreatePort postCreatePort;

    @Override
    public void createPost(PostCreateCommand postCreateCommand) {
        Post post = Post.builder()
            .title(postCreateCommand.title())
            .content(postCreateCommand.content())
            .authorId(postCreateCommand.authorId())
            .build();

        postCreatePort.createPost(post);
    }
}
