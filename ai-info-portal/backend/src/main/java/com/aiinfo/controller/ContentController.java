package com.aiinfo.controller;

import com.aiinfo.entity.Content;
import com.aiinfo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = "*")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Content>> getContentByCategory(@PathVariable String category) {
        List<Content> contentList = contentService.getAllContentByCategory(category);
        return ResponseEntity.ok(contentList);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Content> getContentBySlug(@PathVariable String slug) {
        Content content = contentService.getContentBySlug(slug);
        return ResponseEntity.ok(content);
    }

    @PostMapping
    public ResponseEntity<Content> saveContent(@RequestBody Content content) {
        Content savedContent = contentService.saveContent(content);
        return ResponseEntity.ok(savedContent);
    }
}