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
    public CategoryReadResponse readCategory(CategoryReadQuery categoryReadQuery) {
        CategoryReadRecordQuery categoryReadRecordQuery = CategoryReadRecordQuery.builder()
                .id(categoryReadQuery.id())
                .build();
        CategoryReadRecord categoryReadRecord = categoryReadPort.readCategory(categoryReadRecordQuery);

        return CategoryReadResponse.from(categoryReadRecord);
    }

    @Override
    public List<CategoryReadResponse> readCategories(CategoryReadQuery categoryReadQuery) {

        List<CategoryReadRecord> categoryReadRecords = categoryReadPort.readCategories(CategoryReadRecordQuery.builder().build());
        return categoryReadRecords.stream().map(CategoryReadResponse::from).collect(Collectors.toList());
    }

    @Override
    public List<CategoryTreeReadResponse> readTreeCategories(CategoryTreeReadQuery categoryTreeReadQuery) {
        CategoryTreeReadRecordQuery categoryTreeReadRecordQuery = CategoryTreeReadRecordQuery.builder().build();
        List<CategoryTreeReadRecord> categoryTreeReadRecordList = categoryReadPort.readTreeCategories(categoryTreeReadRecordQuery);

        Map<Long, CategoryTreeReadResponse> categoryTreeReadRecordMap = categoryTreeReadRecordList.stream().map(CategoryTreeReadResponse::from).collect(Collectors.toMap(CategoryTreeReadResponse::id, response -> response));

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
