package org.joonzis.service;

import org.joonzis.vo.ProjVO;

public interface ProjService {
	 //아이디 중복 확인
	 boolean isExistId(String pId);
	 //회원가입
	 boolean join(String pId, String pPw);
	 
	 ProjVO doLogin(String pId, String pPw);
	
	
}