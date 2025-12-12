package org.joonzis.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.joonzis.db.DBConnect;

public class Ex03_delete {
	
	public static void main(String[] args) {
		
	Connection conn = null;
	PreparedStatement ps = null;
	
	try {
			conn = DBConnect.getConnection();
			//박씨 삭제
			String sql = "DELETE FROM sample where name = ?"; 
						
		
			ps = conn.prepareStatement(sql);
			ps.setString(1, "박씨");
		
			int result = ps.executeUpdate();
		
			if(result > 0) {
				System.out.println("데이터 삭제 성공!");
				conn.commit();
			}else {
				System.out.println("데이터 삭제 실패");
			}
		
	} 		catch (Exception e) {
					e.printStackTrace();
			} finally {
				try {
						if(ps != null)ps.close();
						if(conn != null)conn.close();
				}	    catch (Exception e2) {
						e2.printStackTrace();
						}	
		
			}	
	
	}
}
