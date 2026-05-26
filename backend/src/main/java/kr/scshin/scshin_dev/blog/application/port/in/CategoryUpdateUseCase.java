package kr.scshin.scshin_dev.blog.application.port.in;

import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryUpdateCommand;

public interface CategoryUpdateUseCase {
    void updateCategory(CategoryUpdateCommand categoryUpdateCommand);
}
