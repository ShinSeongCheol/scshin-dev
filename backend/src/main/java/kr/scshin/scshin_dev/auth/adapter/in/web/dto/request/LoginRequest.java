package kr.scshin.scshin_dev.auth.adapter.in.web.dto.request;

public record LoginRequest(
        String username,
        String password
) {
}
