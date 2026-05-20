package kr.scshin.scshin_dev.backoffice.adapter.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.*;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.*;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.PostReadRecord;
import kr.scshin.scshin_dev.blog.application.port.in.*;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.*;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryReadResponse;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogServiceAdapter implements PostCreatePort, PostReadPort, PostUpdatePort, CategoryCreatePort, CategoryReadPort {

    private final CategoryCreateUseCase categoryCreateUseCase;
    private final CategoryReadUseCase categoryReadUseCase;

    private final PostCreateUseCase postCreateUseCase;
    private final PostReadUseCase postReadUseCase;
    private final PostUpdateUseCase postUpdateUseCase;

    @Override
    public void createPost(PostCreateRecordCommand postCreateRecordCommand) {
        PostCreateCommand postCreateCommand = new PostCreateCommand(postCreateRecordCommand.title(), postCreateRecordCommand.content(), postCreateRecordCommand.authorId());
        postCreateUseCase.createPost(postCreateCommand);
    }

    @Override
    public List<PostReadRecord> readPostList() {
        List<PostReadResponse> postListResponse = postReadUseCase.readPostList();
        return postListResponse.stream().map(post -> PostReadRecord.builder()
                .id(post.id())
                .title(post.title())
                .content(post.content())
                .authorId(post.authorId())
                .createdAt(post.createdAt())
                .updatedAt(post.updatedAt())
                .views(post.views())
                .build()
        ).toList();
    }

    @Override
    public PostReadRecord readPost(PostReadRecordQuery postReadRecordQuery) {
        PostReadQuery postReadQuery = new PostReadQuery(postReadRecordQuery.postId());
        PostReadResponse postReadResponse = postReadUseCase.readPost(postReadQuery);

        return PostReadRecord.builder()
                .id(postReadResponse.id())
                .title(postReadResponse.title())
                .content(postReadResponse.content())
                .authorId(postReadResponse.authorId())
                .createdAt(postReadResponse.createdAt())
                .updatedAt(postReadResponse.updatedAt())
                .views(postReadResponse.views())
                .build();
    }

    @Override
    public void updatePost(PostUpdateRecordCommand postUpdateRecordCommand) {
        postUpdateUseCase.updatePost(new PostUpdateCommand(postUpdateRecordCommand.id(), postUpdateRecordCommand.title(), postUpdateRecordCommand.content()));
    }

    @Override
    public void createCategory(CategoryCreateRecordCommand categoryCreateRecordCommand) {
        CategoryCreateCommand categoryCreateCommand = CategoryCreateCommand.builder()
                .parentCategoryId(categoryCreateRecordCommand.parentCategoryId())
                .categoryName(categoryCreateRecordCommand.categoryName())
                .slug(categoryCreateRecordCommand.slug())
                .description(categoryCreateRecordCommand.description())
                .useYn(categoryCreateRecordCommand.useYn())
                .build();

        categoryCreateUseCase.createCategory(categoryCreateCommand);
    }

    @Override
    public List<CategoryReadRecord> readCategories(CategoryReadRecordQuery categoryReadRecordQuery) {
        List<CategoryReadResponse> categoryReadResponses = categoryReadUseCase.readCategories(CategoryReadQuery.builder().build());
        return categoryReadResponses.stream().map(categoryReadResponse -> CategoryReadRecord.builder()
                .id(categoryReadResponse.id())
                .parentCategoryId(categoryReadResponse.parentCategoryId())
                .categoryName(categoryReadResponse.categoryName())
                .slug(categoryReadResponse.slug())
                .description(categoryReadResponse.description())
                .sortOrder(categoryReadResponse.sortOrder())
                .depth(categoryReadResponse.depth())
                .useYn(categoryReadResponse.useYn())
                .createdAt(categoryReadResponse.createdAt())
                .updatedAt(categoryReadResponse.updatedAt())
                .build()
        ).toList();
    }
}
