package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.application.port.out.CategoryCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryUpdatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryCreateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryTreeReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryUpdateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryTreeReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryCreatePort, CategoryReadPort, CategoryUpdatePort {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public void createCategory(CategoryCreateRecordCommand categoryCreateRecordCommand) {
        CategoryJpaEntity categoryJpaEntity = CategoryJpaEntity.builder()
                .parentCategoryId(categoryCreateRecordCommand.parentCategoryId())
                .categoryName(categoryCreateRecordCommand.categoryName())
                .description(categoryCreateRecordCommand.description())
                .slug(categoryCreateRecordCommand.slug())
                .useYn(categoryCreateRecordCommand.useYn())
                .sortOrder(categoryCreateRecordCommand.sortOrder())
                .depth(categoryCreateRecordCommand.depth())
                .build();

        categoryJpaRepository.save(categoryJpaEntity);
    }

    @Override
    public CategoryReadRecord readCategory(CategoryReadRecordQuery categoryReadRecordQuery) {
        CategoryJpaEntity categoryJpaEntity = categoryJpaRepository.findById(categoryReadRecordQuery.id()).orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));
        return CategoryReadRecord.from(categoryJpaEntity);
    }

    @Override
    public List<CategoryReadRecord> readCategories(CategoryReadRecordQuery categoryReadRecordQuery) {
        List<CategoryJpaEntity> categoryJpaEntities = categoryJpaRepository.findAll();

        return categoryJpaEntities.stream().map(CategoryReadRecord::from).toList();
    }

    @Override
    public CategoryReadRecord readCategoryByParentCategoryId(CategoryReadRecordQuery categoryReadRecordQuery) {
        CategoryJpaEntity categoryJpaEntity = categoryJpaRepository.findById(categoryReadRecordQuery.parentCategoryId()).orElseThrow(() -> new IllegalArgumentException("부모 카테고리가 존재하지 않습니다."));
        return CategoryReadRecord.from(categoryJpaEntity);
    }

    @Override
    public List<CategoryTreeReadRecord> readTreeCategories(CategoryTreeReadRecordQuery categoryTreeReadRecordQuery) {
        List<CategoryJpaEntity> categoryJpaEntities = categoryJpaRepository.findAll();
        return categoryJpaEntities.stream().map(CategoryTreeReadRecord::from).toList();
    }

    @Override
    public void updateCategory(CategoryUpdateRecordCommand command) {
        CategoryJpaEntity categoryJpaEntity = CategoryJpaEntity.builder()
                .id(command.id())
                .parentCategoryId(command.parentCategoryId())
                .categoryName(command.categoryName())
                .slug(command.slug())
                .description(command.description())
                .depth(command.depth())
                .useYn(command.useYn())
                .build();
        categoryJpaRepository.save(categoryJpaEntity);
    }
}
