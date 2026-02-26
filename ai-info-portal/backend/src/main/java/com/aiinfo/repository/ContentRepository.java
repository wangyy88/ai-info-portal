package com.aiinfo.repository;

import com.aiinfo.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByCategory(String category);
    Optional<Content> findBySlug(String slug);
}