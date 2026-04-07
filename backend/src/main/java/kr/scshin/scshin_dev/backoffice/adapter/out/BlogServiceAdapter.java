package kr.scshin.scshin_dev.backoffice.adapter.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.LoadPostListPort;
import kr.scshin.scshin_dev.backoffice.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogServiceAdapter implements LoadPostListPort {

    @Override
    public List<Post> loadPostList() {
        Post post = new Post(1L, "테스트", "테스트 글입니다.", 1L, LocalDateTime.now(), LocalDateTime.now());
        return List.of(post);
    }
}
