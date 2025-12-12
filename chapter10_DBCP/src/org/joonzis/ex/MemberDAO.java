package org.joonzis.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	// 필드
	private Connection conn =null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 객체 생성자 
	//*싱글톤 (외부에서 접근할 수 없게 private 처리) 
	//객체를 한번만 만듬 내부에서만 new로 만들고 static으로 공유 캘린더.getInstance와 같음
	private MemberDAO() {};
	private static MemberDAO dao = new MemberDAO();
	public static MemberDAO getInstance() {
		return dao;		
	}	
	// DBCP 설정
	private static DataSource ds;
	static {
		try {
			//java.naming 패키지
			javax.naming.Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			//java:comp/env : 톰캣
			//jdbc/oracle : Resource name을 찾아서 ds에 전달
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// 각 메소드
	// 테이블 전체 목록 가져오는 메소드 -getAllList
	public List<MemberVO> getAllList() { //select all 할꺼 getAlllist()파라미터 받을 필요 없다
		List<MemberVO> list = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			sql = "select * from member";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setIdx(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setPw(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setAge(rs.getInt(5));
				vo.setAddr(rs.getString(6));
				vo.setReg_date(rs.getDate(7));
				list.add(vo);			//vo를 list에 담아서 membervo에 넣는다.	
			}
		} 	catch (Exception e) {
			e.printStackTrace();
			try {
				if(rs !=null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	// 데이터 삽입 메소드 - insert
	public int insert(MemberVO vo) { //dao -> db 이런식으로 가야하니 VO 객체를 불러온다.
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "insert into member values" + "(member_seq.nextval, ?, ?, ?, ?, ?, sysdate)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPw());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getAddr());
			
			result = ps.executeUpdate(); //결과데이터 리턴해서 result에 넣는다.
			
			if(result > 0) {
				System.out.println("삽입 성공");
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return result;
	}

	// 데이터 삭제 메소드 - remove
	public int remove(String id, String pw) {
		
		int result = 0;
		try {
			conn = ds.getConnection();
			sql = "delete from member where id=? and pw=?"; 				
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			result = ps.executeUpdate(); 
			
			if(result> 0) {
				conn.commit();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	// id를 통해 유저 정보 가져오는 메소드 - getUserInfoById(String id)
	public MemberVO getUserInfoById(String id) {
		MemberVO vo = null;
		try {
			conn = ds.getConnection();
			sql = "SELECT * FROM member WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setPw(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setAge(rs.getInt(5));
				vo.setAddr(rs.getString(6));
				vo.setReg_date(rs.getDate(7));				
			}			
		}catch (Exception e) {
			e.printStackTrace();
			try {
				if(rs !=null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return vo;
	}
	// 수정할 유저 정보 가져오는 메소드 - getUpdateView
	public MemberVO getUpdateView(String id, String pw) {
		MemberVO vo = null;
		try {
			conn = ds.getConnection();
			sql = "SELECT * FROM member WHERE id=? and pw=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setPw(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setAge(rs.getInt(5));
				vo.setAddr(rs.getString(6));
				vo.setReg_date(rs.getDate(7));				
			}			
		}catch (Exception e) {
			e.printStackTrace();
			try {
				if(rs !=null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return vo;
	}
	// 데이터 수정 메소드 - update		
	public int update(MemberVO vo) {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update member set pw=?, name=?, age=? addr=? where idx =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getPw());
			ps.setString(2, vo.getName());
			ps.setInt(3, vo.getAge());
			ps.setString(4, vo.getAddr());
			ps.setInt(5, vo.getIdx());
			
			result = ps.executeUpdate(); //결과데이터 리턴해서 result에 넣는다.
			
			if(result > 0) {
				System.out.println("수정 성공");
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return result;
	}
	

}
	
	

