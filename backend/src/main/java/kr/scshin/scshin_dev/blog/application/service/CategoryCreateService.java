package kr.scshin.scshin_dev.blog.application.service;

import kr.scshin.scshin_dev.blog.application.port.in.CategoryCreateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryCreateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.blog.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryCreateService implements CategoryCreateUseCase {

    private final CategoryCreatePort categoryCreatePort;
    private final CategoryReadPort categoryReadPort;

    @Override
    public void createCategory(CategoryCreateCommand categoryCreateCommand) {

        int sortOrder = 0;
        int depth = 1;
        if (categoryCreateCommand.parentCategoryId() != null) {
            CategoryReadRecordQuery categoryReadRecordQuery = CategoryReadRecordQuery.builder()
                    .parentCategoryId(categoryCreateCommand.parentCategoryId()
                    ).build();
            CategoryReadRecord categoryReadRecord = categoryReadPort.readCategoryByParentCategoryId(categoryReadRecordQuery);
            depth = categoryReadRecord.depth() + 1;
        }

        Category category = Category.builder()
                .parentCategoryId(categoryCreateCommand.parentCategoryId())
                .categoryName(categoryCreateCommand.categoryName())
                .slug(categoryCreateCommand.slug())
                .description(categoryCreateCommand.description())
                .useYn(categoryCreateCommand.useYn())
                .sortOrder(sortOrder)
                .depth(depth)
                .build();

        CategoryCreateRecordCommand categoryCreateRecordCommand = CategoryCreateRecordCommand.builder()
                .parentCategoryId(category.getParentCategoryId())
                .categoryName(category.getCategoryName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .useYn(category.getUseYn())
                .sortOrder(category.getSortOrder())
                .depth(category.getDepth())
                .build();

        categoryCreatePort.createCategory(categoryCreateRecordCommand);
    }
}
