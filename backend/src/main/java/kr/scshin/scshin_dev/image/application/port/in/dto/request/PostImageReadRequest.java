package kr.scshin.scshin_dev.image.application.port.in.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record PostImageReadRequest(List<Long> postIds) {
}
