package org.joonzis.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.CVO;

public class CDaoImpl implements CDao{
	// DAO 객체 생성
    private static CDaoImpl instance = null;
    private CDaoImpl() {
	}

    public static CDaoImpl getInstance() {
        if (instance == null) {
            instance = new CDaoImpl();
        }
        return instance;
    }
    // 필드
	private static SqlSession sqlsession = null;
	private synchronized static SqlSession getSqlSession() {
		if(sqlsession == null) {
			sqlsession = DBService.getFactory().openSession(false);
		}
		return sqlsession;
	}
	//댓글 등록
    @Override
    public int insertComment(CVO cvo) {
       int result = getSqlSession().insert("insert_comment", cvo);
		if (result > 0) {
			getSqlSession().commit();
		}
		return result;	
        
    }
	//2. 댓글 목록 조회
	@Override
	public List<CVO> getCommList(int b_idx) {
		return getSqlSession().selectList("comm_List", b_idx);
	}

	//  3. 댓글 삭제
	@Override
	public int removeComment(int c_idx) {
		int result = getSqlSession().delete("remove_comm", c_idx);
		if (result > 0) {
			getSqlSession().commit();
		}
		return result;	
	}
}
