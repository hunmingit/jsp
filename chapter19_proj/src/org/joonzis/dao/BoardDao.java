package org.joonzis.dao;

import java.util.List;

import org.joonzis.vo.BoardVO;

public interface BoardDao {
	//공지글 목록 조회(페이징 x)
    List<BoardVO> NoticeList();
    //일반 게시글 목록 조회(페이징) startRow 시작, endRow 끝
    List<BoardVO> BoardList(int startRow, int endRow);
    //전체 게시글 수 조회 (공지글 제외)
    int BoardCount();
    //글쓰기
    void writeBoard(BoardVO vo);;
    //bId로 글 상세보기
	BoardVO boardById(int bIdx);
	//조회수 
	void increaseViews(int bIdx);
	//글 수정
	void editBoard(BoardVO vo);
	//글 삭제
	void deleteBoard(int bIdx);
	//오늘 이 계정이 누른 좋아요 수
	int todayLikeCount(String pId);
	//좋아요 누르기	
	void insertBoardLike(String pId, int bIdx);
	//게시물 좋아요 수 증가
	void increaseBoardLike(int bIdx);

}