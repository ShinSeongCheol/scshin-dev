package kr.scshin.scshin_dev.blog.application.port.in.dto.request;

import lombok.Builder;

@Builder
public record PostReadQuery(
    Long postId
) {
}
