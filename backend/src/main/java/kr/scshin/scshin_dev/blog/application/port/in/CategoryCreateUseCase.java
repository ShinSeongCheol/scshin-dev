package kr.scshin.scshin_dev.blog.application.port.in;

import kr.scshin.scshin_dev.blog.application.port.in.dto.request.CategoryCreateCommand;

public interface CategoryCreateUseCase {
    void createCategory(CategoryCreateCommand categoryCreateCommand);
}
