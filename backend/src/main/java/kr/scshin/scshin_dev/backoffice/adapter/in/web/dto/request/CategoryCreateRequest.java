package kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryCreateRequest(
        Long parentCategoryId,
        @NotBlank(message = "카테고리명은 필수입니다.")
        String categoryName,
        @NotBlank(message = "슬러그는 필수입니다.")
        String slug,
        String description,
        @NotNull
        char useYn
) {
}
