package org.joonzis.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.ProjVO;



public class ProjDaoImpl implements ProjDao {
	
	private static ProjDaoImpl instance = new ProjDaoImpl();
	
	private ProjDaoImpl() {}
	
	public static ProjDaoImpl getInstance() {
	
		return instance;
	}
	
	@Override
	public boolean isExistId(String pId) {
        SqlSession ss = null;
        boolean result = false;

        try {
            ss = DBService.getFactory().openSession();
            int count = ss.selectOne("isExistId", pId);
            result = count > 0;
        } finally {
            if (ss != null) ss.close();
        }

        return result;
    }
	
	@Override
    public int insertMember(ProjVO pvo) {

        SqlSession ss = null;
        int result = 0;

        try {
            ss = DBService.getFactory().openSession(false); // autoCommit false
            result = ss.insert("insertProj", pvo);
            ss.commit();
        } catch (Exception e) {
            if(ss != null) ss.rollback();
            e.printStackTrace();
        } finally {
            if(ss != null) ss.close();
        }

        return result;
    }
	
	@Override
	public ProjVO doLogin(String pId, String pPw) {
	
		SqlSession ss = null;
		ProjVO vo = null;
		
		try {
			ss = DBService.getFactory().openSession();
			vo = ss.selectOne("loginProj", 
					Map.of("pId", pId, "pPw", pPw));
			
		} finally {
			if(ss != null) ss.close();
		}
		return vo;
	}
	
	
}



