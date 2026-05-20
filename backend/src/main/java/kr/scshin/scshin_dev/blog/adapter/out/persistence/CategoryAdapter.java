package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryReadQuery;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryCreateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryCreatePort, CategoryReadPort {

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
    public List<CategoryReadRecord> readCategories(CategoryReadRecordQuery categoryReadRecordQuery) {
        List<CategoryJpaEntity> categoryJpaEntities = categoryJpaRepository.findAll();

        return categoryJpaEntities.stream().map(categoryJpaEntity -> CategoryReadRecord.builder()
                .id(categoryJpaEntity.getId())
                .parentCategoryId(categoryJpaEntity.getParentCategoryId())
                .categoryName(categoryJpaEntity.getCategoryName())
                .description(categoryJpaEntity.getDescription())
                .slug(categoryJpaEntity.getSlug())
                .useYn(categoryJpaEntity.getUseYn())
                .sortOrder(categoryJpaEntity.getSortOrder())
                .depth(categoryJpaEntity.getDepth())
                .createdAt(categoryJpaEntity.getCreatedAt())
                .updatedAt(categoryJpaEntity.getUpdatedAt())
                .build()
        ).toList();
    }
}
