package org.joonzis.service;

import org.joonzis.dao.ProjDao;
import org.joonzis.dao.ProjDaoImpl;
import org.joonzis.util.PasswordUtil;
import org.joonzis.vo.ProjVO;

public class ProjServiceImpl implements ProjService{
	private static ProjServiceImpl instance = new ProjServiceImpl();
	private ProjDao pdao = ProjDaoImpl.getInstance();
	private ProjServiceImpl() {}
	
    public static ProjServiceImpl getInstance() {
        return instance;
    }	

    @Override
    public boolean isExistId(String pId) {
    	
    	return pdao.isExistId(pId);
    }
    
	@Override
	public boolean join(String pId, String pPw) {
	    // 아이디 중복 체크 (서버 기준)
	    if(pdao.isExistId(pId)) {
	        return false;
	    }
	
	    // 비밀번호 암호화
	    String encPw = PasswordUtil.sha256(pPw);
	    
	    ProjVO pvo = new ProjVO();
	    pvo.setpId(pId);
	    pvo.setpPw(encPw);
	
	    // 회원가입
	    int result = pdao.insertMember(pvo);
	
	    return result > 0;
		
	}
	
	@Override
	public ProjVO doLogin(String pId, String pPw) {
		// 비밀번호 암호화
	    String encPw = PasswordUtil.sha256(pPw);
	    return pdao.doLogin(pId, encPw);
	    
	}
	
}




