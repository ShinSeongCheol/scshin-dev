package kr.scshin.scshin_dev.common.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.text.TextContentRenderer;
import org.springframework.stereotype.Service;

@Service
public class CommonMarkParserService {
    public String renderHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public String renderAsSummary(String markdown, int length) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);

        TextContentRenderer textRenderer = TextContentRenderer.builder().build();
        String plainText = textRenderer.render(document);

        plainText = plainText.replaceAll("\\s+", "").trim();
        if (plainText.length() > length) {
            plainText = plainText.substring(0, length) + "...";
        }

        return "<p>" + plainText + "</p>";
    }
}
