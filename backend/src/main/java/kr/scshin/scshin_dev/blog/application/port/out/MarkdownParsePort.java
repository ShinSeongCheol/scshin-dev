package kr.scshin.scshin_dev.blog.application.port.out;

import java.util.List;

public interface MarkdownParsePort {
    String renderAsSummary(String markdown);
    List<String> extractImageUrls(String markdown);
}
