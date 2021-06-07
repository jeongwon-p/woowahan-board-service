package com.woowahan.woowahanboardservice.domain.board.dao;

import com.woowahan.woowahanboardservice.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    List<Board> findAllByHiddenFalse();
}
