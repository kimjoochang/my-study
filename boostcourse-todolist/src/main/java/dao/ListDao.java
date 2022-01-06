package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import dto.List;

public class ListDao {
	private static String dburl = "jdbc:mysql://localhost:3306/todolist";
	private static String dbuser = "root";
	private static String dbpasswd = "1234";
	
	public int addList(List list) { //추가하기
		int insertCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			String sql = "INSERT INTO list(content, name, priority, time) VALUES (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,list.getContent());
			ps.setString(2,list.getName());
			ps.setInt(3,list.getPriority());
			ps.setString(4,list.getTime());
			
			insertCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch (Exception ex) {}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch (Exception ex) {}
			}
		 }
		
		return insertCount;
	}
	
	public List getList(String content) { //출력하기
		List list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			String sql = "SELECT content, name, priority, date_format(time,'&Y.%m.%d') FROM list WHERE content = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, content);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				String contents = rs.getString("content");
				String name = rs.getString("name");
				int priority = rs.getInt("priority");
				String time = rs.getString("time");
				list = new List(contents,name,priority,time);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 }
		
	
		return list;
	}
}
