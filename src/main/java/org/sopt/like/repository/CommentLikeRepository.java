package org.sopt.like.repository;

import org.sopt.like.domain.CommentLike;
import org.sopt.comment.domain.Comment;
import org.sopt.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByUserAndComment(User user, Comment comment);  // 이미 좋아요를 눌렀는지 확인
    Optional<CommentLike> findByUserAndComment(User user, Comment comment); // 특정 사용자와 댓글에 대한 좋아요 조회
}
