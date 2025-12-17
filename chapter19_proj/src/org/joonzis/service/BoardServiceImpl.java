package org.joonzis.service;

import java.util.List;

import org.joonzis.dao.BoardDao;
import org.joonzis.dao.BoardDaoImpl;
import org.joonzis.vo.BoardVO;
import org.joonzis.service.BoardService;
import org.joonzis.util.PageMaker;

public class BoardServiceImpl implements BoardService{
	//dao연결
    private BoardDao boardDAO = BoardDaoImpl.getInstance();
    //게시글 페이지당 글 개수
    private static final int PER_PAGE = 10;
	
    //공지글 목록
    @Override
    public List<BoardVO> getNoticeList() {
    	return boardDAO.selectNoticeList();
    }
    //일반 게시글 목록 (페이징)
    @Override
    public List<BoardVO> getBoardList(int page) {
    	//주소값에 0들어와도 서버 안터지게
    	if (page < 1) page = 1;
        int startRow = (page - 1) * PER_PAGE + 1; //페이지가 1이면 (1-1)*10 +1 = 1 -> 1부터
        int endRow = page * PER_PAGE; // 페이지가 1이면 1*10 = 10 -> 10까지
        
        return boardDAO.selectBoardList(startRow, endRow);
    }
    //전체 게시글 수
    @Override
    public int getBoardCount() {
    	return boardDAO.selectBoardCount();
    }
    //페이지네이션 객체 생성 int page왜 가져가냐 int page = 지금 사용자가 보고 있는 페이지 번호이고, pagemaker가 페이지 버튼(이전/다음/숫자)을 계산하기 위함
    //selectBoardCount();에서 totalCount가져옴, PER_PAGE= 10 으로 상수 박고 PageMaker에 보냄
    @Override
    public PageMaker getPageMaker(int page) {
        int totalCount = boardDAO.selectBoardCount();
        return new PageMaker(page, totalCount, PER_PAGE);
    }
    //글쓰기
    @Override
    public void writeBoard(BoardVO vo) {
        boardDAO.writeBoard(vo);
    }
    //id로 글 가져와 상세보기
    @Override
    public BoardVO boardById(int bIdx) {
    	return boardDAO.boardById(bIdx);
    }
    //조회수
    @Override
    public void increaseViews(int bIdx) {
    	boardDAO.increaseViews(bIdx);   	
    }
    //글 수정
    @Override
    public void editBoard(BoardVO vo) {
    	boardDAO.editBoard(vo);   	
    }
    //글 삭제
    @Override
    public void deleteBoard(int bIdx) {
    	boardDAO.deleteBoard(bIdx); 
    }
}




