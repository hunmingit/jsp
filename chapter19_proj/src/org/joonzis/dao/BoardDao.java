package org.joonzis.dao;

import java.util.List;

import org.joonzis.vo.BoardVO;

public interface BoardDao {
	//공지글 목록 조회(페이징 x)
    List<BoardVO> selectNoticeList();
    //일반 게시글 목록 조회(페이징) startRow 시작, endRow 끝
    List<BoardVO> selectBoardList(int startRow, int endRow);
    //전체 게시글 수 조회 (공지글 제외)
    int selectBoardCount();
}