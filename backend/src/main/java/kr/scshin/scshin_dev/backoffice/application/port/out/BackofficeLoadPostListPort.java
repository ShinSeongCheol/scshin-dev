package kr.scshin.scshin_dev.backoffice.application.port.out;

import java.util.List;

public interface BackofficeLoadPostListPort {
    List<BackofficePostListResult> loadPostList();
}
