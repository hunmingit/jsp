package org.joonzis.service;

import java.util.List;
import org.joonzis.util.PageMaker;
import org.joonzis.vo.BoardVO;

public interface BoardService {
	//공지글 목록 조회 (페이징 x 상단 고정)
	List<BoardVO> getNoticeList();
	//일반 게시글 목록 조회 (페이징 o)
	List<BoardVO> getBoardList(int page);
	//전체 일반 게시글 개수(공지글 제외)
	int getBoardCount();
	//페이지 정보 생성 사용자가 클릭한 페이지 번호를 전달
	PageMaker getPageMaker(int page);
	//글쓰기
	void writeBoard(BoardVO vo);
	//bId로 글 상세보기
	BoardVO boardById(int bIdx);
	//조회수
	void increaseViews(int bIdx);
	//글 수정
	void editBoard(BoardVO vo);
	//글 삭제
	void deleteBoard(int bIdx);
}