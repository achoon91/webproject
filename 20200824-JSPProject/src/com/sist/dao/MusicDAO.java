package com.sist.dao;
/*
 *   제어문(출력) , 변수(데이터 모우기) ,
 *   예외처리(오라클 연결) 
 *   
 *   오라클 연결 => ArrayList => add() , get() , size() 
 *   => 출력 => for-each
 */
import java.sql.*; // Connection,PreparedStatement,ResultSet
import java.util.*;// ArrayList 
public class MusicDAO {
   // 연결 
	private Connection conn;
	// SQL 전송
	private PreparedStatement ps;
	// 오라클 주소 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// 1. 실제 오라클 연결해주는 드라이버 oracle.jdbc.driver.OracleDriver
	public MusicDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 드라이버를 이용해서 연결 => thin드라이버 
		}catch(Exception ex) {}
	}
	// 2. 연결 메소드
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			// conn hr/happy
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
	// DAO(JDBC => 기본 셋팅)
	// 기능 => MusicTop200
	public ArrayList<MusicVO> musicAllData()
	{
		ArrayList<MusicVO> list=
				new ArrayList<MusicVO>();
		try
		{
			getConnection();// 연결
			// SQL문장 전송
			String sql="SELECT mno,title,singer,album,poster "
					  +"FROM genie_music "
					  +"ORDER BY mno";
			ps=conn.prepareStatement(sql);
			// 결과값 받기
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo=new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
				vo.setPoster(rs.getString(5));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	// 영화 => 출력 
	public ArrayList<MovieVO> movieAllData()
	{
		ArrayList<MovieVO> list=new ArrayList<MovieVO>();
		try
		{
			getConnection();
			String sql="SELECT no,title,poster,regdate,genre,grade "
					+ "FROM daum_movie "
					+ "WHERE cateno=1 "
					+ "ORDER BY no";
			
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MovieVO vo=new MovieVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setGenre(rs.getString(5));
				vo.setGrade(rs.getString(6));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
}








