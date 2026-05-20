package kr.scshin.scshin_dev.backoffice.application.service;

import kr.scshin.scshin_dev.backoffice.application.port.in.CategoryReadUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.CategoryReadQuery;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.CategoryReadResponse;
import kr.scshin.scshin_dev.backoffice.application.port.out.CategoryReadPort;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service(value = "BackofficeCategoryReadService")
@RequiredArgsConstructor
public class CategoryReadService implements CategoryReadUseCase {

    private final CategoryReadPort categoryReadPort;

    @Override
    public List<CategoryReadResponse> readCategories(CategoryReadQuery categoryReadQuery) {
        CategoryReadRecordQuery categoryReadRecordQuery = CategoryReadRecordQuery.builder().build();
        List<CategoryReadRecord> categoryReadRecords = categoryReadPort.readCategories(categoryReadRecordQuery);

        return categoryReadRecords.stream().map(categoryReadRecord -> CategoryReadResponse.builder()
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
                .build()
        ).toList();
    }
}
