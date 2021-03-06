package com.woowahan.woowahanboardservice.domain.board.dao;

import com.woowahan.woowahanboardservice.domain.board.dto.request.ArticleReportRequest;
import com.woowahan.woowahanboardservice.domain.board.entity.Article;
import com.woowahan.woowahanboardservice.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findAllByCreateDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
