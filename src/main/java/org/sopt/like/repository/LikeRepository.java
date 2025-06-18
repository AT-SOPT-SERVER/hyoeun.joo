package org.sopt.like.repository;

import org.sopt.like.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, Long> {
}
