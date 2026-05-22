package kr.scshin.scshin_dev.blog.application.port.in;

import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryTreeReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryReadResponse;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryTreeReadResponse;

import java.util.List;

public interface CategoryReadUseCase {
    List<CategoryReadResponse> readCategories(CategoryReadQuery categoryReadQuery);
    List<CategoryTreeReadResponse> readTreeCategories(CategoryTreeReadQuery categoryTreeReadQuery);
}
