package org.joonzis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.BoardVO;

public class BoardDaoImpl implements BoardDao {
	//싱글톤
	private static BoardDaoImpl instance = new BoardDaoImpl();
	public static BoardDaoImpl getInstance() {
		return instance;
	}
	
    private SqlSession sqlSession;
    private BoardDaoImpl() {
        sqlSession = DBService.getFactory().openSession(true); //오토커밋 insert / update / delete 시 commit 자동
    }
	//공지글 목록
    @Override
    public List<BoardVO> selectNoticeList() {
    	return sqlSession.selectList("NoticeList");
    }
    
    //일반 게시글 목록(페이징)
    @Override
    public List<BoardVO> selectBoardList(int startRow, int endRow) {
        Map<String, Object> map = new HashMap<>();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSession.selectList("BoardList", map);
    }
    //전체 게시글 수
    @Override
    public int selectBoardCount() {
    	return sqlSession.selectOne("BoardCount");
    }
    //글쓰기
    @Override
    public void writeBoard(BoardVO vo) {
        sqlSession.insert("insertBoard", vo);
    }
    //글 상세 보기
    @Override
    public BoardVO boardById(int bIdx) {
    	return sqlSession.selectOne("boardById", bIdx);
    }
    //조회수
    @Override
    public void increaseViews(int bIdx) {
    	sqlSession.update("increaseViews", bIdx);  	
    }
    //글 수정하기
    @Override
    public void editBoard(BoardVO vo) {
    	sqlSession.update("editBoard", vo); 	
    }
    //글 삭제
    @Override
    public void deleteBoard(int bIdx) {
    	sqlSession.delete("deleteBoard", bIdx);   	
    }
	
}



