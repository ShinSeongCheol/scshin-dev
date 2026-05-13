package kr.scshin.scshin_dev.blog.application.service;


import kr.scshin.scshin_dev.blog.application.port.in.PostUpdateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostUpdateCommand;
import kr.scshin.scshin_dev.blog.application.port.out.*;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.*;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostImageReadRecord;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostReadRecord;
import kr.scshin.scshin_dev.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service(value = "BlogPostUpdateService")
@RequiredArgsConstructor
public class PostUpdateService implements PostUpdateUseCase {

    private final PostReadPort postReadPort;
    private final PostUpdatePort postUpdatePort;
    private final MarkdownParsePort markdownParsePort;
    private final PostImageUpdatePort postImageUpdatePort;
    private final PostImageReadPort postImageReadPort;

    @Override
    @Transactional
    public void updatePost(PostUpdateCommand postUpdateCommand) {
        PostReadRecord postReadRecord = postReadPort.readPost(new PostReadRecordQuery(postUpdateCommand.id()));

        Post post = Post.builder()
                .id(postReadRecord.id())
                .title(postReadRecord.title())
                .content(postReadRecord.content())
                .authorId(postReadRecord.authorId())
                .createdAt(postReadRecord.createdAt())
                .updateAt(postReadRecord.updatedAt())
                .build();
        post.update(postUpdateCommand.title(), postUpdateCommand.content());

        List<String> imageUrls = markdownParsePort.extractImageUrls(post.getContent());
        List<String> fileNames = imageUrls.stream().map(imageUrl -> imageUrl.substring(imageUrl.lastIndexOf("/") + 1)).toList();
        Set<String> fileNameSet = new HashSet<>(fileNames);

        // 삭제된 이미지 post id null 처리
        List<PostImageReadRecord> postImageReadRecordList = postImageReadPort.readPostImageList(PostImageReadRecordQuery.builder().postId(post.getId()).build());
        List<PostImageReadRecord> deletedPostImageReadResponseList = postImageReadRecordList.stream().filter(postImageReadResponse -> !fileNameSet.contains(postImageReadResponse.storedName())).toList();
        postImageUpdatePort.updatePostIdToNull(PostImageUpdateRecordCommand.builder()
                .postId(post.getId())
                .fileNames(deletedPostImageReadResponseList.stream().map(PostImageReadRecord::storedName).toList())
            .build()
        );

        // 글에 등록된 이미지 업데이트
        postImageUpdatePort.updatePostImage(PostImageUpdateRecordCommand.builder()
                .postId(post.getId())
                .fileNames(fileNames)
            .build()
        );

        postUpdatePort.updatePost(new PostUpdateRecordCommand(post.getId(), post.getTitle(), post.getContent(), post.getAuthorId()));
    }
}
