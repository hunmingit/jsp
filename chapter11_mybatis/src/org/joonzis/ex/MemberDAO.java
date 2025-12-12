
package org.joonzis.ex;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.joonzis.mybatis.DBService;



public class MemberDAO {
	// 필드
	private SqlSessionFactory factory = null;
	
	// 싱글톤
	// 기본 생성자( 외부에서 접근할 수 없게 private 처리)
	private MemberDAO() {
		factory = DBService.getFactory();
	}
	private static MemberDAO dao = new MemberDAO();
	public static MemberDAO getInstance() {
		return dao;
	}
	
	/*
	 * openSession()			select 문에서 사용(커밋을 하지 않음)
	 * openSession(true)		자동 커밋
	 * openSession(false)		수동 커밋
	 * 
	 * SqlSession의 메소드 종류
	 * 	1. selectList("mapper에서 사용할 id")	 select all			
	 * -- 검색 결과가 여러 개 일때 List(테이블)
	 * 	2. selectList("mapper에서 사용할 id", 파라미터) 파라미터는 한개만 던짐	
	 * -- 파라미터 전달 시
	 * 	3. selectOne("mapper에서 사용할 id")				
	 * -- 검색 결과가 한 개 일 때(테이블)
	 * 	4. selectOne("mapper에서 사용할 id", 파라미터)
	 * 	5. insert("mapper에서 사용할 id", 파라미터)
	 * 	6. update("mapper에서 사용할 id")
	 * 	7. delete("mapper에서 사용할 id")
	 * 
	 *   *** 각 메소드의 두번 째 인자 값으로 파라미터를 던질 수 있다.
	 *   던질 파라미터가 없을 시 생략
	 */
	//전체 검색
	public List<MemberVO> getAllList() {
		SqlSession session = factory.openSession();
		List<MemberVO> list = session.selectList("select_all"); //rs담을 필요없이
		session.close();
		return list;
	}
	//회원 추가
	public int insert(MemberVO vo) {
		SqlSession session = factory.openSession(false); //수동 커밋
		int result = session.insert("insert", vo); //mapper에 인서트 id에 vo를 던짐 
		if(result>0) {
			session.commit();
		}
		session.close();
		return result;			
	}
	//회원 1명 
	public MemberVO getUserInfoById(String id) {
		MemberVO vo = new MemberVO();
		SqlSession session = factory.openSession();
		vo = session.selectOne("select_one", id);		
		session.close();
		return vo;
	}
	// 데이터 삭제 메소드 - remove
	public int remove(String id, String pw) {
		SqlSession session = factory.openSession(false);
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		int result = 0;
		result = session.delete("remove", vo);
		if(result>0) {
			session.commit();
		}
		session.close();
		return result;
	}
	
	//수정할 유정 정보 페이지
	public MemberVO getUpdateView(String id, String pw) {
		SqlSession session = factory.openSession(false);
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		MemberVO resultVo = session.selectOne("update_page", vo);
		session.close();
		return resultVo;

	}
	// 데이터 수정 메소드 - update		
	public int update(MemberVO vo) {
		SqlSession session = factory.openSession(false); //수동 커밋
		
		int result = session.update("update", vo); //mapper에 인서트 id에 vo를 던짐 
		if(result>0) {
			session.commit();
		}
		session.close();
		return result;			
	}
}










