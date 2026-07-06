package kr.scshin.scshin_dev.blog.application.service;

import jakarta.transaction.Transactional;
import kr.scshin.scshin_dev.blog.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadDetailResponse;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import kr.scshin.scshin_dev.blog.application.port.out.*;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryPostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryPostReadRecord;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostImageReadRecord;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "BlogPostReadService")
@RequiredArgsConstructor
public class PostReadService implements PostReadUseCase {

    private final PostReadPort postReadPort;
    private final MarkdownParsePort markdownParsePort;
    private final PostImageReadPort postImageReadPort;
    private final PostViewIncreasePort postViewIncreasePort;

    private final CategoryPostReadPort categoryPostReadPort;

    @Override
    public List<PostReadResponse> readPostList() {
        List<PostReadRecord> postReadRecords = postReadPort.readPostList();
        List<List<PostImageReadRecord>> postImages = postImageReadPort.readPostImageLists(PostImageReadRecordQuery.builder()
                .postIds(postReadRecords.stream().map(PostReadRecord::id).toList())
                .build());

        Map<Long, List<PostImageReadRecord>> imageMap = postImages.stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(PostImageReadRecord::postId));

        return postReadRecords.stream().map(post ->
                PostReadResponse.builder()
                        .id(post.id())
                        .title(post.title())
                        .content(markdownParsePort.renderAsSummary(post.content()))
                        .authorId(post.authorId())
                        .createdAt(post.createdAt())
                        .updatedAt(post.updatedAt())
                        .imageUrls(imageMap.getOrDefault(post.id(), Collections.emptyList()).stream().map(image -> "/uploads/" + image.filePath() + "/" + image.storedName()).collect(Collectors.toList()))
                        .views(post.views())
                        .build()
                ).toList();
    }

    @Override
    public PostReadDetailResponse readPost(PostReadQuery postReadQuery) {
        PostReadRecord postReadRecord = postReadPort.readPost(new PostReadRecordQuery(postReadQuery.postId()));
        List<CategoryPostReadRecord> categoryPostReadRecord = categoryPostReadPort.readCategoryPost(new CategoryPostReadRecordQuery(postReadQuery.postId()));

        log.info("read post record: {}", postReadRecord);
        log.info("read category post read record: {}", categoryPostReadRecord);

        return PostReadDetailResponse.builder()
                .id(postReadRecord.id())
                .title(postReadRecord.title())
                .content(markdownParsePort.renderAsMarkdown(postReadRecord.content()))
                .authorId(postReadRecord.authorId())
                .createdAt(postReadRecord.createdAt())
                .updatedAt(postReadRecord.updatedAt())
                .views(postReadRecord.views())
                .categoryIds(categoryPostReadRecord.stream().map(CategoryPostReadRecord::categoryId).collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional
    public PostReadResponse readPostAsHtml(PostReadQuery postReadQuery) {
        PostReadRecord postReadRecord = postReadPort.readPost(new PostReadRecordQuery(postReadQuery.postId()));

        postViewIncreasePort.increasePostViewCount(postReadRecord.id());

        return PostReadResponse.builder()
                .id(postReadRecord.id())
                .title(postReadRecord.title())
                .content(markdownParsePort.renderAsHtml(postReadRecord.content()))
                .authorId(postReadRecord.authorId())
                .createdAt(postReadRecord.createdAt())
                .updatedAt(postReadRecord.updatedAt())
                .build();
    }
}
