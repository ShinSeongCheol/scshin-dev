package kr.scshin.scshin_dev.backoffice.adapter.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.*;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.*;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryTreeReadRecord;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.PostReadDetailRecord;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.PostReadRecord;
import kr.scshin.scshin_dev.blog.application.port.in.*;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.*;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryReadResponse;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryTreeReadResponse;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadDetailResponse;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogServiceAdapter implements PostCreatePort, PostReadPort, PostUpdatePort, CategoryCreatePort, CategoryReadPort, CategoryUpdatePort {

    private final CategoryCreateUseCase categoryCreateUseCase;
    private final CategoryReadUseCase categoryReadUseCase;
    private final CategoryUpdateUseCase categoryUpdateUseCase;

    private final PostCreateUseCase postCreateUseCase;
    private final PostReadUseCase postReadUseCase;
    private final PostUpdateUseCase postUpdateUseCase;

    @Override
    public void createPost(PostCreateRecordCommand postCreateRecordCommand) {
        PostCreateCommand postCreateCommand = PostCreateCommand.from(postCreateRecordCommand);
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
    public PostReadDetailRecord readPost(PostReadRecordQuery postReadRecordQuery) {
        PostReadQuery postReadQuery = new PostReadQuery(postReadRecordQuery.postId());
        PostReadDetailResponse postReadDetailResponse = postReadUseCase.readPost(postReadQuery);

        return PostReadDetailRecord.builder()
                .id(postReadDetailResponse.id())
                .title(postReadDetailResponse.title())
                .content(postReadDetailResponse.content())
                .authorId(postReadDetailResponse.authorId())
                .createdAt(postReadDetailResponse.createdAt())
                .updatedAt(postReadDetailResponse.updatedAt())
                .views(postReadDetailResponse.views())
                .categoryIds(postReadDetailResponse.categoryIds())
                .build();
    }

    @Override
    public void updatePost(PostUpdateRecordCommand postUpdateRecordCommand) {

        postUpdateUseCase.updatePost(
                PostUpdateCommand.builder()
                .id(postUpdateRecordCommand.id())
                .title(postUpdateRecordCommand.title())
                .content(postUpdateRecordCommand.content())
                .categories(postUpdateRecordCommand.categories())
                .build()
        );
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
    public CategoryReadRecord readCategory(CategoryReadRecordQuery categoryReadRecordQuery) {
        CategoryReadResponse categoryReadResponse = categoryReadUseCase.readCategory(CategoryReadQuery.builder().id(categoryReadRecordQuery.id()).build());
        return CategoryReadRecord.from(categoryReadResponse);
    }

    @Override
    public List<CategoryReadRecord> readCategories(CategoryReadRecordQuery categoryReadRecordQuery) {
        List<CategoryReadResponse> categoryReadResponses = categoryReadUseCase.readCategories(CategoryReadQuery.builder().build());
        return categoryReadResponses.stream().map(CategoryReadRecord::from).toList();
    }

    @Override
    public List<CategoryTreeReadRecord> readTreeCategories(CategoryTreeReadRecordQuery categoryTreeReadRecordQuery) {
        CategoryTreeReadQuery categoryTreeReadQuery = CategoryTreeReadQuery.builder().build();
        List<CategoryTreeReadResponse> categoryTreeReadResponses = categoryReadUseCase.readTreeCategories(categoryTreeReadQuery);

        return categoryTreeReadResponses.stream().map(CategoryTreeReadRecord::from).toList();
    }

    @Override
    public void updateCategory(CategoryUpdateRecordCommand command) {
        CategoryUpdateCommand categoryUpdateCommand = CategoryUpdateCommand.builder()
                .id(command.id())
                .parentCategoryId(command.parentCategoryId())
                .categoryName(command.categoryName())
                .slug(command.slug())
                .description(command.description())
                .useYn(command.useYn())
                .build();

        categoryUpdateUseCase.updateCategory(categoryUpdateCommand);
    }
}
