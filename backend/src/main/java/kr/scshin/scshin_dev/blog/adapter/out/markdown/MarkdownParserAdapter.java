package kr.scshin.scshin_dev.blog.adapter.out.markdown;

import kr.scshin.scshin_dev.blog.application.port.out.MarkdownParsePort;
import lombok.extern.slf4j.Slf4j;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MarkdownParserAdapter implements MarkdownParsePort {
    private final Parser parser = Parser.builder().build();
    private final TextContentRenderer textRenderer = TextContentRenderer.builder().build();

    @Override
    public String renderAsSummary(String markdown) {
        Node document = parser.parse(markdown);
        return textRenderer.render(document);
    }
}
