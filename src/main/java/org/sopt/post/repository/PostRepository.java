package org.sopt.post.repository;

import org.sopt.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);

    List<Post> findByTitleContaining(String keyword);

    Page<Post> findAll(Pageable pageable);

}
