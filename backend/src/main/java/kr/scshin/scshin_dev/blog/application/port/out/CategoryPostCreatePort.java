package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryPostCreateRecordCommand;

import java.util.List;

public interface CategoryPostCreatePort {
    void createCategoryPost(List<CategoryPostCreateRecordCommand>  categoryPostCreateRecordCommandList);
}
