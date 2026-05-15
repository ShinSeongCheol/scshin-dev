package kr.scshin.scshin_dev.blog.application.port.out;

import java.util.List;

public interface MarkdownParsePort {
    String renderAsMarkdown(String markdown);
    String renderAsSummary(String markdown);
    String renderAsHtml(String markdown);
    List<String> extractImageUrls(String markdown);
}
