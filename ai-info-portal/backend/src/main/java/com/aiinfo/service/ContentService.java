package com.aiinfo.service;

import com.aiinfo.entity.Content;
import com.aiinfo.repository.ContentRepository;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    private final Parser parser;
    private final HtmlRenderer htmlRenderer;

    public ContentService() {
        this.parser = Parser.builder()
                .extensions(Arrays.asList(TablesExtension.create()))
                .build();
        this.htmlRenderer = HtmlRenderer.builder()
                .extensions(Arrays.asList(TablesExtension.create()))
                .build();
    }

    public List<Content> getAllContentByCategory(String category) {
        return contentRepository.findByCategory(category);
    }

    public Content getContentBySlug(String slug) {
        return contentRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Content not found: " + slug));
    }

    public Content saveContent(Content content) {
        // Convert markdown to HTML
        Node document = parser.parse(content.getMarkdownContent());
        String html = htmlRenderer.render(document);
        content.setHtmlContent(html);

        // Set slug from title if not provided
        if (content.getSlug() == null || content.getSlug().isEmpty()) {
            content.setSlug(generateSlug(content.getTitle()));
        }

        return contentRepository.save(content);
    }

    private String generateSlug(String title) {
        return title.toLowerCase()
                .replaceAll("[^a-z0-9\\u4e00-\\u9fa5\\s]", "")
                .trim()
                .replaceAll("\\s+", "-");
    }
}