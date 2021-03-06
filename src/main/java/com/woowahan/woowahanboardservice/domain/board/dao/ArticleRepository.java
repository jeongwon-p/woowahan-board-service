package com.woowahan.woowahanboardservice.domain.board.dao;

import com.woowahan.woowahanboardservice.domain.board.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    Page<Article> findAllByBoardIdAndHiddenFalse(String boardId, Pageable pageable);

    List<Article> findAllByUserIdAndCreateDateTimeBetween(String userId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Article> findAllByCreateDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
