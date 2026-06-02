package kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String categoryName) {
}
