package kr.scshin.scshin_dev.backoffice.application.service;

import kr.scshin.scshin_dev.backoffice.application.port.in.PostUpdateUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostUpdateCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostUpdatePort;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostUpdateRecordCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostUpdateService implements PostUpdateUseCase {

    private final PostUpdatePort postUpdatePort;

    @Override
    public void updatePost(PostUpdateCommand postUpdateCommand) {
        postUpdatePort.updatePost(new PostUpdateRecordCommand(postUpdateCommand.id(), postUpdateCommand.title(), postUpdateCommand.content()));
    }
}
