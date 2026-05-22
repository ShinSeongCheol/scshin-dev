package kr.scshin.scshin_dev.blog.application.service;

import kr.scshin.scshin_dev.blog.application.port.in.CategoryReadUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryTreeReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryReadResponse;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryTreeReadResponse;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryTreeReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryTreeReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryReadService implements CategoryReadUseCase {

    private final CategoryReadPort categoryReadPort;

    @Override
    public List<CategoryReadResponse> readCategories(CategoryReadQuery categoryReadQuery) {

        List<CategoryReadRecord> categoryReadRecords = categoryReadPort.readCategories(CategoryReadRecordQuery.builder().build());
        return categoryReadRecords.stream().map(categoryReadRecord -> CategoryReadResponse.builder()
                .id(categoryReadRecord.id())
                .parentCategoryId(categoryReadRecord.parentCategoryId())
                .categoryName(categoryReadRecord.categoryName())
                .description(categoryReadRecord.description())
                .slug(categoryReadRecord.slug())
                .sortOrder(categoryReadRecord.sortOrder())
                .depth(categoryReadRecord.depth())
                .useYn(categoryReadRecord.useYn())
                .createdAt(categoryReadRecord.createdAt())
                .updatedAt(categoryReadRecord.updatedAt())
                .build()
        ).toList();
    }

    @Override
    public List<CategoryTreeReadResponse> readTreeCategories(CategoryTreeReadQuery categoryTreeReadQuery) {
        CategoryTreeReadRecordQuery categoryTreeReadRecordQuery = CategoryTreeReadRecordQuery.builder().build();
        List<CategoryTreeReadRecord> categoryTreeReadRecordList = categoryReadPort.readTreeCategories(categoryTreeReadRecordQuery);

        Map<Long, CategoryTreeReadResponse> categoryTreeReadRecordMap = categoryTreeReadRecordList.stream().map(record -> CategoryTreeReadResponse.builder()
                .id(record.id())
                .parentCategoryId(record.parentCategoryId())
                .categoryName(record.categoryName())
                .description(record.description())
                .slug(record.slug())
                .sortOrder(record.sortOrder())
                .depth(record.depth())
                .useYn(record.useYn())
                .createdAt(record.createdAt())
                .updatedAt(record.updatedAt())
                .childrenList(new ArrayList<>())
                .build()
        ).collect(Collectors.toMap(CategoryTreeReadResponse::id, response -> response));

        List<CategoryTreeReadResponse> rootCategories = new ArrayList<>();
        for (CategoryTreeReadResponse node : categoryTreeReadRecordMap.values()) {
            Long parentId = node.parentCategoryId();
            if (parentId == null || parentId == 0L) {
                rootCategories.add(node);
            }else {
                CategoryTreeReadResponse parentNode = categoryTreeReadRecordMap.get(parentId);
                if (parentNode != null) {
                    parentNode.childrenList().add(node);
                }
            }
        }

        return rootCategories;
    }
}
