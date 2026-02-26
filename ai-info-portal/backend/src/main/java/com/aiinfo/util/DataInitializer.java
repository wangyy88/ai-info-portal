package com.aiinfo.util;

import com.aiinfo.entity.Content;
import com.aiinfo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ContentService contentService;

    @Override
    public void run(String... args) throws Exception {
        initializeSampleData();
    }

    private void initializeSampleData() throws IOException {
        // Check if data already exists
        if (!contentService.getAllContentByCategory("AI_HISTORY").isEmpty()) {
            System.out.println("Sample data already exists, skipping initialization.");
            return;
        }

        // Load and save AI History content
        loadAndSaveContent(
            "AI_HISTORY",
            "ai-development-overview",
            "AI发展史概述",
            "content/ai_history/overview.md"
        );

        // Load and save Popular Products content
        loadAndSaveContent(
            "POPULAR_PRODUCTS",
            "popular-ai-products-overview",
            "热门AI产品概述",
            "content/popular_products/overview.md"
        );

        // Load and save AI Encyclopedia content
        loadAndSaveContent(
            "AI_ENCYCLOPEDIA",
            "ai-terminology-encyclopedia",
            "AI术语百科",
            "content/ai_encyclopedia/definitions.md"
        );

        System.out.println("Sample data initialized successfully.");
    }

    private void loadAndSaveContent(String category, String slug, String title, String resourcePath) throws IOException {
        // 从classpath加载资源
        ClassPathResource resource = new ClassPathResource(resourcePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

        String markdownContent = reader.lines()
            .collect(Collectors.joining("\n"));

        Content content = new Content();
        content.setCategory(category);
        content.setSlug(slug);
        content.setTitle(title);
        content.setMarkdownContent(markdownContent);

        contentService.saveContent(content);
    }
}