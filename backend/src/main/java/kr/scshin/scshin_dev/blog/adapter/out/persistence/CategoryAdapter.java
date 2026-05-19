package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryCreateRecordCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryCreatePort {

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
}
