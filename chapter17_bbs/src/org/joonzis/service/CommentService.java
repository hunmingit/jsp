package org.joonzis.service;

import java.util.List;


import org.joonzis.vo.CVO;

public interface CommentService {
	//1. 댓글 삽입
    public int insertComment(CVO cvo);

    //2. 댓글 목록 조회
    public List<CVO> getCommList(int b_idx);
    
    //3. 댓글 삭제
    public int removeComment(int c_idx);
}
