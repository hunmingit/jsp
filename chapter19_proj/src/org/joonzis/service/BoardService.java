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
	
}