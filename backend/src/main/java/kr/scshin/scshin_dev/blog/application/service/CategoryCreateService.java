package kr.scshin.scshin_dev.blog.application.service;

import kr.scshin.scshin_dev.blog.application.port.in.CategoryCreateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryCreateRecordCommand;
import kr.scshin.scshin_dev.blog.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryCreateService implements CategoryCreateUseCase {

    private final CategoryCreatePort categoryCreatePort;

    @Override
    public void createCategory(CategoryCreateCommand categoryCreateCommand) {

        Category category = Category.builder()
                .parentCategoryId(categoryCreateCommand.parentCategoryId())
                .categoryName(categoryCreateCommand.categoryName())
                .slug(categoryCreateCommand.slug())
                .description(categoryCreateCommand.description())
                .useYn(categoryCreateCommand.useYn())
                .sortOrder(0)
                .depth(1)
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
