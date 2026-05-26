package kr.scshin.scshin_dev.backoffice.application.service;

import kr.scshin.scshin_dev.backoffice.application.port.in.CategoryCreateUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.CategoryCreateCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.CategoryCreatePort;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.CategoryCreateRecordCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "BackofficeCategoryCreateService")
@RequiredArgsConstructor
public class CategoryCreateService implements CategoryCreateUseCase {

    private final CategoryCreatePort categoryCreatePort;

    @Override
    public void createCategory(CategoryCreateCommand categoryCreateCommand) {
        CategoryCreateRecordCommand categoryCreateRecordCommand = CategoryCreateRecordCommand.builder()
                .parentCategoryId(categoryCreateCommand.parentCategoryId())
                .categoryName(categoryCreateCommand.categoryName())
                .slug(categoryCreateCommand.slug())
                .description(categoryCreateCommand.description())
                .useYn(categoryCreateCommand.useYn())
                .build();

        categoryCreatePort.createCategory(categoryCreateRecordCommand);
    }
}
