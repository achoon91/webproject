package com.sist.dao;
import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MovieDAO {
    private static SqlSessionFactory ssf; // XML�� �б�
    // �ڵ� �ʱ�ȭ ==> Ŭ������ ������ �ڵ����� ó�� 
    static 
    {
    	try
    	{
    		// XML�б� => �ּ� => �ѱ��� �б� (�ѱ�:2byte => Reader)
    		Reader reader=Resources.getResourceAsReader("Config.xml");
    		// Config => movie-mapper.xml,databoard-mapper.xml�� ���� 
    		// Config.xml�� ~mapper.xml�� �ݵ�� ���
    		// �Ľ� : XML�� ��ϵǾ� �ִ� �������߿� �ʿ��� �����͸� �о�� ���� ==>SqlSessionFactory
    		ssf=new SqlSessionFactoryBuilder().build(reader);
    		// ���α׷��Ӱ� XML�Ľ��� ���� �ʰ� => ���̺귯���� �Ľ� �޼ҵ尡 �ִ� 
    		// build ==> �Ľ��ϴ� �޼ҵ� (SAX) => �±׸� �Ѱ��� �о �����͸� ���� 
    		// mybatis�� �������� �±�,�Ӽ��� �ݵ�� ��� ==> dtd (�±�,�Ӽ� ����)
    		// Spring => XML�� �ʿ��� ������ ���� => Spring ���̺귯���� �о�� ���� ����
    		/*
    		 *   XML�� �̿��ϴ� ���� 
    		 *   => �ڹ� �ҽ��� �������� �ʴ´� => ���ۿ� �ʿ��� �����͸� XML�� �÷��ָ� �о��
    		 *   => ��� ���̺귯���� �ҽ��� �������� �ʴ´� (�����Ҽ� �ִ� ���ϸ� ���� .class)
    		 *   => �ǹ����� �����Ҷ� �ڹټҽ��� �������� �ʴ´� (������ .class�� ����)
    		 *   => ���� (war) => jar(.class)
    		 */
    	}catch(Exception ex)
    	{
    		// XML�� ������ ó��
    		ex.printStackTrace();
    	}
    }
    // SQL������ �о ���� => ��û�� �����͸� �Ѱ��ش� (SQL => movie-mapper.xml)
    // <select id="movieListData" resultType="com.sist.dao.MovieVO" parameterType="hashmap">
    // sql���� ������ ��µ� ����� vo�� ��� �������� ��� ����ſ�
    /*
     *   SELECT no,poster,title
     *   FROM daum_movie;
     *   
     *   ==> no,poster,title ==> StringTokenizer => no,poster,title
     */
    public static List<MovieVO> movieListData(Map map)
    {
    	// map => start��ȣ , end��ȣ
    	List<MovieVO> list=new ArrayList<MovieVO>();
    	// list�� ���� ä��� ==> home.jsp�� ä���� list���� ��� 
    	// ����Ŭ ���� 
    	SqlSession session=null;
        // ���� => ����� ��Ƽ� ó��
    	try
    	{
    		// ����
    		session=ssf.openSession(); // COMMIT�� ��뿩�� 
    		/*
    		 *   INSERT,UPDATE,DELETE :����Ŭ ����� �����Ͱ� ���� => �ٽ� ���� ��û : COMMIT
    		 *   ssf.openSession() => COMMIT(X)
    		 *   ssf.openSession(true) => COMMIT(O)
    		 */
    		// SQL������ �����Ŀ� �����͸� �о list�� ���� 
    		list=session.selectList("movieListData",map);
    	}catch(Exception ex)
    	{
    		// XML => ������ ���� ��쿡 ��� �߻��ߴ��� Ȯ��
    		ex.printStackTrace();
    	}
    	finally
    	{
    		// �̸� ������ Connection�� �ٽ� ������� ���� ��ȯ => DBCP
    		// DataBase Connection Pool(Connection�� �����ϴ� ����)
    		if(session!=null)
    			session.close();
    	}
    	return list;
    }
    // �������� 
    // <select id="movieTotalPage" resultType="int">
    public static int movieTotalPage()
    {
    	int total=0;
    	// ���� 
    	SqlSession session=null;
    	try
    	{
    		// ����
    		session=ssf.openSession();
    		// SQL���� ������ ����� �ޱ�
    		total=session.selectOne("movieTotalPage");
    	}catch(Exception ex)
    	{
    		// ���� ó��
    		ex.printStackTrace();
    	}
    	finally
    	{
    		// �ݱ� => �ڵ����� ��ȯ 
    		if(session!=null)
    			session.close();
    	}
    	return total;
    }
    // <select id="movieCategoryData" resultType="com.sist.dao.MovieVO" parameterType="int">
    public static List<MovieVO> movieCategoryData(int cateno)
    {
    	List<MovieVO> list=new ArrayList<MovieVO>();
    	// ����
    	SqlSession session=null;
    	try
    	{
    		// ������ ��ü ��� 
    		session=ssf.openSession();
    		// SQL���� �������Ŀ� ������� �޴´� 
    		list=session.selectList("movieCategoryData",cateno);
    	}catch(Exception ex)
    	{
    		// ���� ó��
    		ex.printStackTrace();
    	}
    	finally
    	{
    		// �ݱ�
    		if(session!=null)
    			session.close();
    	}
    	return list;
    }
    // �󼼺��� : XML�� �ڵ� => Mybatis�� ó�� ��û
    // <select id="movieDetailData" resultType="com.sist.dao.MovieVO" parameterType="int">
    // ó���� DAO(�ڹ�) ==> ó���� ��� �������� ���� ==> JSP�� �޾Ƽ� ȭ�鿡 ���
    // JSP(��ũ) ==> movie-mapper.xml(SQL) ==> MovieDAO���� SQL���� ���� ��� ==> JSP
    // home                                                                detail
    public static MovieVO movieDetailData(int no)
    {
    	MovieVO vo=new MovieVO();
    	// ����
    	SqlSession session=null;
    	try
    	{
    		// ���� �õ�
    		session=ssf.openSession();
    		// SQL������ ���� => ������� ���� 
    		vo=session.selectOne("movieDetailData", no);
    	}catch(Exception ex)
    	{
    		// ���� ó��
    		ex.printStackTrace();
    	}
    	finally
    	{
    		// �ݱ� ==> ������ �����ϰ� ��ȯ
    		if(session!=null)
    			session.close();
    	}
    	return vo;
    }
}

