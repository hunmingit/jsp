package org.joonzis.service;

import java.util.List;
import org.joonzis.model.Criteria;
import org.joonzis.vo.BVO;

public interface BBSService {
//	public List<BVO> getList(); 
	//페이징 처리된 게시글 목록 가져오기
	public List<BVO> getListWithPaging(Criteria cri);
	//1. 게시글 삽입
	public int InsertBBS(BVO bvo);
	//2. 게시글 상세보기
	public BVO getBBS(int b_idx);
	//3. 게시글 삭제
	public int removeBBS(int b_idx_remove);
	//4. 게시글 수정
	public int updateBBS(BVO bvo); //void로 써도됨 어차피 리턴해서 안쓰기 때문에
	//5. 게시글 조회수 증가
	public int updateHit(BVO bvo);

	//전체 게시글 수 가져오기
	public int getTotalRecordCount();
}
