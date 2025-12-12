package org.joonzis.service;

import java.util.List;
import org.joonzis.dao.BDao;
import org.joonzis.dao.BDaoImpl;
import org.joonzis.model.Criteria;
import org.joonzis.vo.BVO;

public class BBSServiceImpl implements BBSService {
	private BDao bdao = BDaoImpl.getInstance();
	
	// @Override
	// public List<BVO> getList() {
	// 	return bdao.getList();
	// }
	//페이징 처리된 게시글 목록 가져오기
    @Override
    public List<BVO> getListWithPaging(Criteria cri) {
        return bdao.getListWithPaging(cri);
    }
	
	//1. 게시글 삽입
	@Override
	public int InsertBBS(BVO bvo) {
		return bdao.InsertBBS(bvo);
	}
	//2. 게시글 상세보기
	@Override
	public BVO getBBS(int b_idx) {
		return bdao.getBBS(b_idx);
	}
	//3. 게시글 삭제
	@Override
	public int removeBBS(int b_idx_remove) {
		return bdao.removeBBS(b_idx_remove);
	}
	//4. 게시글 수정
	@Override
	public int updateBBS(BVO bvo) {
		return bdao.updateBBS(bvo);
	}
	//5. 게시글 조회수 증가
	@Override
	public int updateHit(BVO bvo) {
		return bdao.updateHit(bvo);
	}
	//전체 게시글 수 가져오기
    @Override
    public int getTotalRecordCount() {
        return bdao.getTotalRecordCount();
    }

}










