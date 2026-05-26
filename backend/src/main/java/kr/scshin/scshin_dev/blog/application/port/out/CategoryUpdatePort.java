package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryUpdateRecordCommand;

public interface CategoryUpdatePort {
    void updateCategory(CategoryUpdateRecordCommand command);
}
