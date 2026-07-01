package kr.scshin.scshin_dev.auth.adapter.in.web.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record UserInfoResponse(
        String sub,
        String scope,
        Instant iat,
        Instant exp
) {
}
