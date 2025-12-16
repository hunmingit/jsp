package org.joonzis.dao;

import org.joonzis.vo.ProjVO;

public interface ProjDao {
	boolean isExistId(String pId);
	int insertMember(ProjVO pvo);
	ProjVO doLogin(String pId, String pPw);
}