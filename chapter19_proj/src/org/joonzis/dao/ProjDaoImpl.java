package org.joonzis.dao;

import org.apache.ibatis.session.SqlSession;
import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.ProjVO;

public class ProjDaoImpl implements ProjDao {
	private static ProjDaoImpl instance = null;
	private ProjDaoImpl() {}
	public static ProjDaoImpl getInstance() {
		if(instance == null) {
			instance = new ProjDaoImpl();
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
	

}



