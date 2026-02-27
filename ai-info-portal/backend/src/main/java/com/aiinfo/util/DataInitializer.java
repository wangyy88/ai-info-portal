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

        // Load and save AI Future Trends content
        loadAndSaveContent(
            "AI_HISTORY",
            "ai-future-trends",
            "人工智能未来发展展望",
            "content/ai_history/future_trends.md"
        );

        // Load and save Popular Products content
        loadAndSaveContent(
            "POPULAR_PRODUCTS",
            "popular-ai-products-overview",
            "热门AI产品概述",
            "content/popular_products/overview.md"
        );

        // Load and save ChatGPT content
        loadAndSaveContent(
            "POPULAR_PRODUCTS",
            "chatgpt-revolution",
            "ChatGPT：改变世界的AI对话模型",
            "content/popular_products/chatgpt.md"
        );

        // Load and save AI Encyclopedia content
        loadAndSaveContent(
            "AI_ENCYCLOPEDIA",
            "ai-terminology-encyclopedia",
            "AI术语百科",
            "content/ai_encyclopedia/definitions.md"
        );

        // Load and save Deep Learning content
        loadAndSaveContent(
            "AI_ENCYCLOPEDIA",
            "deep-learning-explained",
            "深度学习详解",
            "content/ai_encyclopedia/deep_learning.md"
        );

        // Load and save LLM content
        loadAndSaveContent(
            "AI_ENCYCLOPEDIA",
            "llm-explained",
            "大语言模型(LLM)详解",
            "content/ai_encyclopedia/llm.md"
        );

        // Load and save Prompt Engineering content
        loadAndSaveContent(
            "AI_ENCYCLOPEDIA",
            "prompt-engineering",
            "提示工程(Prompt Engineering)",
            "content/ai_encyclopedia/prompt_engineering.md"
        );

        // Load and save Claude content
        loadAndSaveContent(
            "POPULAR_PRODUCTS",
            "claude-assistant",
            "Claude：Anthropic的智能对话助手",
            "content/popular_products/claude.md"
        );

        // Load and save Midjourney content
        loadAndSaveContent(
            "POPULAR_PRODUCTS",
            "midjourney-art",
            "Midjourney：AI艺术创作工具",
            "content/popular_products/midjourney.md"
        );

        // Load and save AI Safety and Ethics content
        loadAndSaveContent(
            "AI_HISTORY",
            "ai-safety-ethics",
            "AI安全与伦理",
            "content/ai_history/ai_safety_ethics.md"
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