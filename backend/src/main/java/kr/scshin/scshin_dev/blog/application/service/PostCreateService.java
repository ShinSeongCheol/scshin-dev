package kr.scshin.scshin_dev.blog.application.service;

import kr.scshin.scshin_dev.blog.application.port.in.PostCreateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.out.MarkdownParsePort;
import kr.scshin.scshin_dev.blog.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostCreateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostCreateRecord;
import kr.scshin.scshin_dev.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service(value = "BlogPostCreateService")
@RequiredArgsConstructor
public class PostCreateService implements PostCreateUseCase {

    private final PostCreatePort postCreatePort;
    private final MarkdownParsePort markdownParsePort;

    @Override
    @Transactional
    public void createPost(PostCreateCommand postCreateCommand) {
        Post post = Post.builder()
            .title(postCreateCommand.title())
            .content(postCreateCommand.content())
            .authorId(postCreateCommand.authorId())
            .build();

        PostCreateRecord postCreateRecord = postCreatePort.createPost(new PostCreateRecordCommand(post.getTitle(), post.getContent(), post.getAuthorId()));
        Post savedPost = Post.builder()
                .id(postCreateRecord.id())
                .title(postCreateRecord.title())
                .content(postCreateRecord.content())
                .authorId(postCreateRecord.authorId())
                .createdAt(postCreateRecord.createdAt())
                .updateAt(postCreateRecord.updatedAt())
                .build();

        List<String> imageUrls = markdownParsePort.extractImageUrls(savedPost.getContent());
        for(String imageUrl : imageUrls) {
            log.info(imageUrl);
        }

        // POST_ID 획득
        // Markdown Url 파싱 파일 고유 아이디로 IMAGE status, post_id 업데이트
    }
}
