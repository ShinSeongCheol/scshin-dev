package kr.scshin.scshin_dev.blog.adapter.out.markdown;

import kr.scshin.scshin_dev.blog.application.port.out.MarkdownParsePort;
import lombok.extern.slf4j.Slf4j;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.Image;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> extractImageUrls(String markdown) {
        Node document = parser.parse(markdown);
        List<String> imageUrls = new ArrayList<>();

        document.accept(new AbstractVisitor() {
            @Override
            public void visit(Image image) {
                imageUrls.add(image.getDestination());
                visitChildren(image);
            }
        });

        return imageUrls;
    }
}
