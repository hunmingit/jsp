package org.joonzis.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.joonzis.db.DBConnect;

public class Ex04_select {
	
	public static void main(String[] args) {
		
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;  //select에서만 사용
	
	try {
			conn = DBConnect.getConnection();		
			String sql = "select * from sample";		
			ps = conn.prepareStatement(sql); //쿼리문 등록		
			rs = ps.executeQuery(); //select의 리턴 타입
		
			while(rs.next()) {
				System.out.println(rs.getInt(1) + ", ");
				System.out.println(rs.getString(2) + ", ");
				System.out.println(rs.getDate(3));
			}
		
	} 		catch (Exception e) {
					e.printStackTrace();
			} finally {
						try {
							if(ps != null)ps.close();
							if(rs != null)rs.close();
							if(conn != null)conn.close();
						}catch (Exception e2) {
							e2.printStackTrace();
						}	
		
			}	
	
	}
}
