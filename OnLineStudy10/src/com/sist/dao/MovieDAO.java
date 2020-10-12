package com.sist.dao;

import java.sql.*; // Connection,PreparedStatement,ResultSet
import javax.sql.*; // �����ͺ��̽� ������ ��Ƽ� ó��: DataSource
import javax.naming.*; // jdbc/oracle => connection��ü�� �ּҸ� ã�� ���
import java.io.*;
import java.util.*; // ArrayList����� ���� import

/**
 * @author ���±�
 *
 *         Connectionpool ==> ���� ==> connection ��ü ����Ҷ��� �޶�����. ==> ��������
 *         Connection�� ���� ���������� ==> ������ �̸� ������� �ֱ� ������ ������� �ּҸ� ���´�
 * 
 */
public class MovieDAO {
   private Connection conn;
   private PreparedStatement ps;
/*
   // MyBatis ���� ����� ==> POOLED
   public void getConnection() { // �̸� ������ Connection�� ���´�.
      try {
         // ���� ==> POOL�� ����(POOL��! CONNECTION ��ü ���� �����̴�)
         Context init = new InitialContext(); // Ǯ�� �����ϴ� Ing..
         Context c = (Context) init.lookup("java://comp/env");
         DataSource ds = (DataSource) c.lookup("jdbc/oracle");
         // POOL => ��������(�̸�����) ���丮�� ��������ִ�. (�ٿ��� JNDI)
         conn = ds.getConnection();
      } catch (Exception e) {
         e.getMessage();
      }
   }
   */
    public void getConnection()// �̸� ������ connection�� ���´� 
     {
        try
        {
           // ���� => POOL�� ���� (POOL => Connection��ü ���� ����)
           Context init=new InitialContext();
           Context c=(Context)init.lookup("java://comp/env");
           DataSource ds=(DataSource)c.lookup("jdbc/oracle");
           // POOL => �������� (�̸�) ���丮�� ����� �ִ� (JNDI)
           conn=ds.getConnection();
        }catch(Exception ex)
        {
           System.out.println(ex.getMessage());
        }
     }
   public void disConnection() // ����Ŀ� ��ȯ
   {
      try {
         if (ps != null)
            ps.close();
         if (conn != null)
            conn.close();
      } catch (Exception e) {
      }
   }

   // ��� => ����
    public List<String> empGetName()
     {
        List<String> list=new ArrayList<String>();
        try
        {
           getConnection();// �ּ� ���
           String sql="SELECT ename FROM emp";
           ps=conn.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
              list.add(rs.getString(1));
           }
           rs.close();
        }catch(Exception ex)
        {
           System.out.println(ex.getMessage());
        }
        finally
        {
           disConnection();//��ȯ
        }
        return list;
     }
   //��Ĺ => �������� ���� java�ҽ� main������ ������ ������ 
   public static void main(String[] args) {
      MovieDAO dao = new MovieDAO();
      List<String> list=dao.empGetName();
      for(String s:list)
      {
         System.out.println(s);
      }
   }
   public List<MovieVO> movieListData(int page)
   {
      List<MovieVO> list = new ArrayList<MovieVO>();
      try 
      {
    	  getConnection();
    	  int rowSize=20;
    	  int start=(rowSize*page)-(rowSize-1);
    	  int end=rowSize*page;
    	  
    	  String sql="SELECT no,title,poster,num "
    			  +"FROM (SELECT no,title,poster,rownum as num "
    			  +"FROM (SELECT no,title,poster "
    			  +"FROM daum_movie ORDER BY no ASC)) "
    			  +"WHERE num BETWEEN "+start+" AND "+end;
    	  ps=conn.prepareStatement(sql);
    	  ResultSet rs=ps.executeQuery();
    	  while(rs.next())
    	  {
    		  MovieVO vo=new MovieVO();
    		  vo.setNo(rs.getInt(1));
    		  vo.setTitle(rs.getString(2));
    		  vo.setPoster(rs.getString(3));
    		  
    		  list.add(vo);
    	  }
    	  rs.close();
      }catch (Exception e) 
      {
    	  System.out.println(e.getMessage());
      }finally {
         
      }
      
      return list;
      
      
      
   }
   /*
    * getConnection() SQL���� ���� ����� �޾Ƽ� ArrayList�� ����ش�.
    */
}