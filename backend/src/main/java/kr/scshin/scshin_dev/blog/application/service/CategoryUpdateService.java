package kr.scshin.scshin_dev.blog.application.service;

import kr.scshin.scshin_dev.blog.application.port.in.CategoryUpdateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryUpdateCommand;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryUpdatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryUpdateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.blog.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryUpdateService implements CategoryUpdateUseCase {

    private final CategoryReadPort categoryReadPort;
    private final CategoryUpdatePort categoryUpdatePort;

    @Override
    @Transactional
    public void updateCategory(CategoryUpdateCommand categoryUpdateCommand) {

        CategoryReadRecordQuery categoryReadRecordQuery = CategoryReadRecordQuery.builder()
                .id(categoryUpdateCommand.id())
                .parentCategoryId(categoryUpdateCommand.parentCategoryId())
                .build();

        CategoryReadRecord categoryReadRecord = categoryReadPort.readCategory(categoryReadRecordQuery);

        int depth = 1;
        if (categoryUpdateCommand.parentCategoryId() != null) {
            CategoryReadRecord parentCategoryReadRecord = categoryReadPort.readCategoryByParentCategoryId(categoryReadRecordQuery);
            depth = parentCategoryReadRecord.depth() + 1;
        }

        Category category = Category.builder()
                .id(categoryReadRecord.id())
                .parentCategoryId(categoryReadRecord.parentCategoryId())
                .categoryName(categoryReadRecord.categoryName())
                .slug(categoryReadRecord.slug())
                .description(categoryReadRecord.description())
                .sortOrder(categoryReadRecord.sortOrder())
                .depth(categoryReadRecord.depth())
                .useYn(categoryReadRecord.useYn())
                .createdAt(categoryReadRecord.createdAt())
                .updatedAt(categoryReadRecord.updatedAt())
                .build();

        category.update(categoryUpdateCommand.parentCategoryId(), categoryUpdateCommand.categoryName(), categoryUpdateCommand.slug(), categoryUpdateCommand.description(), depth, categoryUpdateCommand.useYn());

        CategoryUpdateRecordCommand categoryUpdateRecordCommand = CategoryUpdateRecordCommand.builder()
                .id(category.getId())
                .parentCategoryId(category.getParentCategoryId())
                .categoryName(category.getCategoryName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .depth(category.getDepth())
                .useYn(category.getUseYn())
                .build();

        categoryUpdatePort.updateCategory(categoryUpdateRecordCommand);
    }
}
