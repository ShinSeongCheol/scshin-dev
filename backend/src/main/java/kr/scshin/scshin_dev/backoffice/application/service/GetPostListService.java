package kr.scshin.scshin_dev.backoffice.application.service;

import kr.scshin.scshin_dev.backoffice.application.port.in.GetPostListUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.out.LoadPostListPort;
import kr.scshin.scshin_dev.backoffice.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostListService implements GetPostListUseCase {

    private final LoadPostListPort loadPostListPort;

    @Override
    public List<Post> getPostList() {
        return loadPostListPort.loadPostList();
    }
}
