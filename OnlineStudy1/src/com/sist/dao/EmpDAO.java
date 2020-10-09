package com.sist.dao;
import java.util.*;
import java.sql.*;
public class EmpDAO {
	
	private Connection conn;
	// SQL 전송
	private PreparedStatement ps;
	// 오라클 주소 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public EmpDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	// 2. 연결 메소드
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");		
		}catch(Exception ex) {}
	}
	// 3. 닫는 메소드 
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			// exit 
		}catch(Exception ex) {}
	}
	
	public ArrayList<EmpVO> empListData()
	{
		ArrayList<EmpVO> list=new ArrayList<EmpVO>();
		try
		{
			getConnection();
			String sql="SELECT empno,ename,job,hiredate,sal FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	
}
