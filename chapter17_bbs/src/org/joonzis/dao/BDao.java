package org.joonzis.dao;

import java.util.List;

import org.joonzis.model.Criteria;
import org.joonzis.vo.BVO;

public interface BDao {
	
	public List<BVO> getListWithPaging(Criteria cri);
	public int InsertBBS(BVO bvo);	
	public BVO getBBS(int b_idx);
	public int removeBBS(int b_idx_remove);
	public int updateBBS(BVO bvo);
	public int updateHit(BVO bvo);

	//전체 게시글 수 가져오기
	public int getTotalRecordCount();
}

