package kr.scshin.scshin_dev.backoffice.adapter.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.CategoryCreatePort;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostReadPort;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostUpdatePort;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.CategoryCreateRecordCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostCreateRecordCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostUpdateRecordCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.PostReadRecord;
import kr.scshin.scshin_dev.blog.application.port.in.CategoryCreateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.PostCreateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.PostUpdateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostUpdateCommand;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogServiceAdapter implements PostCreatePort, PostReadPort, PostUpdatePort, CategoryCreatePort {

    private final CategoryCreateUseCase categoryCreateUseCase;

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
                .useYn(categoryCreateRecordCommand.useYn())
                .build();

        categoryCreateUseCase.createCategory(categoryCreateCommand);
    }
}
