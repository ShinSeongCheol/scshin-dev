package kr.scshin.scshin_dev.backoffice.application.service;

import jakarta.validation.Valid;
import kr.scshin.scshin_dev.backoffice.application.port.in.CreatePostUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostCreateCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.backoffice.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "BackofficeCreatePostService")
@RequiredArgsConstructor
public class PostCreateService implements CreatePostUseCase {

    private final PostCreatePort postCreatePort;

    @Override
    public void createPost(@Valid PostCreateCommand postCreateCommand) {
        Post post = Post.builder()
            .title(postCreateCommand.title())
            .content(postCreateCommand.content())
            .authorId(postCreateCommand.authorId())
            .build();

        postCreatePort.createPost(post);
    }
}
