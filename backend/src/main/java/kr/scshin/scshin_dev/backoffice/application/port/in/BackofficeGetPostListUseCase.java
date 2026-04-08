package kr.scshin.scshin_dev.backoffice.application.port.in;

import java.util.List;

public interface BackofficeGetPostListUseCase {
    List<BackofficePostListResponse> getPostList();
}
