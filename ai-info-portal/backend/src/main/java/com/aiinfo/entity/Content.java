package com.aiinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category; // AI_HISTORY, POPULAR_PRODUCTS, AI_ENCYCLOPEDIA

    @Column(nullable = false, unique = true)
    private String slug; // URL-friendly identifier

    @Column(length = 10000, columnDefinition = "TEXT")
    private String markdownContent;

    @Column(length = 10000, columnDefinition = "TEXT")
    private String htmlContent;

    @Column(nullable = false)
    private String title;

    // Constructors
    public Content() {}

    public Content(String category, String slug, String markdownContent, String htmlContent, String title) {
        this.category = category;
        this.slug = slug;
        this.markdownContent = markdownContent;
        this.htmlContent = htmlContent;
        this.title = title;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}