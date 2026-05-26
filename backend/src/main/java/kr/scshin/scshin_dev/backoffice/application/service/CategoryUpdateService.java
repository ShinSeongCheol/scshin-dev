package kr.scshin.scshin_dev.backoffice.application.service;

import kr.scshin.scshin_dev.backoffice.application.port.in.CategoryUpdateUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.CategoryUpdateCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.CategoryUpdatePort;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.CategoryUpdateRecordCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "BackofficeCategoryUpdateService")
@RequiredArgsConstructor
public class CategoryUpdateService implements CategoryUpdateUseCase {

    private final CategoryUpdatePort categoryUpdatePort;

    @Override
    @Transactional
    public void updateCategory(CategoryUpdateCommand command) {
        CategoryUpdateRecordCommand categoryUpdateRecordCommand = CategoryUpdateRecordCommand.builder()
                .id(command.id())
                .parentCategoryId(command.parentCategoryId())
                .categoryName(command.categoryName())
                .slug(command.slug())
                .description(command.description())
                .useYn(command.useYn())
                .build();
        categoryUpdatePort.updateCategory(categoryUpdateRecordCommand);
    }
}
