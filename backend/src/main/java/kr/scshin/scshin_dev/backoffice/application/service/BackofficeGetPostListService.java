package kr.scshin.scshin_dev.backoffice.application.service;

import kr.scshin.scshin_dev.backoffice.application.port.in.BackofficeGetPostListUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.BackofficePostListResponse;
import kr.scshin.scshin_dev.backoffice.application.port.out.BackofficeLoadPostListPort;
import kr.scshin.scshin_dev.backoffice.application.port.out.BackofficePostListResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BackofficeGetPostListService implements BackofficeGetPostListUseCase {

    private final BackofficeLoadPostListPort loadPostListPort;

    @Override
    public List<BackofficePostListResponse> getPostList() {
        List<BackofficePostListResult> postListResult = loadPostListPort.loadPostList();
        return postListResult.stream().map(postResult -> new BackofficePostListResponse(postResult.id(), postResult.title(), postResult.content(), postResult.authorId(), postResult.createdAt(), postResult.updatedAt())).toList();
    }
}
