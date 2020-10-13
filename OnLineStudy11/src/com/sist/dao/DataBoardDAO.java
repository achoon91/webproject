package com.sist.dao;
// XML�Ľ��Ŀ� => ����� ����� �޴� ��ġ=>DAO���� �޼ҵ� ȣ�� 
import java.io.*;//XML���� �б� => Reader (�ѱ�����)
import java.util.*;//List => ���� (ArrayList)
/*
 *    IO (Input,Output) : ������ ����� 
 *    ==============================
 *      1. �޸� ����� 
 *      2. ���� ����� 
 *      3. ��Ʈ��ũ ����� 
 *    ==============================
 *    IO�� ���� (1byte,2byte)
 *    1byte=> ����Ʈ ��Ʈ��(���� ���ε�,�ٿ�ε�) => ~InputStream,~OutputStream
 *    2byte=> ���� ��Ʈ�� (�ѱ����Ե� ������ �б�) => ~Reader , ~Writer
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class DataBoardDAO {
   // XML�� �Ľ�(������ �б�) => ���� ������ ���� ==> SqlSesionFactory
   private static SqlSessionFactory ssf;
   // �ʱ�ȭ => XML�� �б�(�ڵ����� ����)
   static 
   {
	   try
	   {
		   // 1. XML���� �б�
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   // Config�� ��ϵǾ� �ִ� ��� XML���� �д´� 
		   // ���ϸ� ��ҹ��ڸ� �����Ѵ�
		   // 2. XML�� �Ľ� : �ʿ��� ������ �о� ���� (���̹�Ƽ�� ���̺귯���� �о� ����) => ������ �ϰ� ����Ѵ�
		   ssf=new SqlSessionFactoryBuilder().build(reader);
		   // �Ľ� => SAX(�б�����) => �±׸� �Ѱ��� �о�ͼ� �ʿ䵥���͸� �����ϴ� ���
		   /*
		    *    JAXP 
		    *      DOM :����,�б�,����,�߰� (�޸𸮿��� ����)
		    *           => �ӵ��� �ʴ� 
		    *      SAX : �б� => �Ϲ������� ��� ���̺귯���� SAX����� ����Ѵ�
		    *                   MyBatis,Spring...
		    *           => �ӵ��� ������ 
		    *    JAXB : ������ (������ �ڹٿ� ���� ä���ش�)
		    *      binding
		    */
	   }catch(Exception ex)
	   {
		   // ����ó�� 
		   System.out.println(ex.getMessage());
	   }   
   }
   // ��� ��� ==> id="boardListData"
   //            ������ : resultType            //�Ű����� : parameterType
   // select ==> selectList() : row�� ������ �϶� ,  selectOne() : row�� �Ѱ��϶� 
   // ����Ŭ==> ���� => row(record)
   // <select id="boardListData" resultType="DataBoardVO" parameterType="hashmap">
   public static List<DataBoardVO> boardListData(Map map)
   {
	   List<DataBoardVO> list=new ArrayList<DataBoardVO>();
	   // Connection�� ����� ������ �ݵ�� ��ȯ ==> SqlSession
	   SqlSession session=null;
	   // SqlSession => Connection�� ������ ���� ���� 
	   // ������ ����ó���� ���� => �ʹݿ� ����ó���ϱ� ���ؼ� => ����ó���� �ϴ� ���� ���� => null
	   try
	   {
		   // ���� : ������� : try���� => finally����
		   //      ������  : �߰��� catch���� => finally����
		   // 1. �̸� ���� Connection��ü�� ��� �´� 
		   session=ssf.openSession();
		   // 2. XML�� �ִ� SQL������ �����Ŀ� ������� �޶�
		   list=session.selectList("boardListData",map);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();//�����ϴ� ������ �����ش� 
	   }
	   finally
	   {
		   // ��ȯ => Connection=>close()
		   if(session!=null)
			   session.close(); 
		   /*
		    *   close()
		    *   {
		    *     if(ps!=null) ps.close();
		    *     if(conn!=null) conn.close(); ==> disConnection()
		    *   }
		    */
	   }
	   return list;
   }
   // �������� ==> id="boardTotalPage"
   // <select id="boardTotalPage" resultType="int">
   public static int boardTotalPage()
   {
	   // ���������� �ݵ�� �ʱ�ȭ 
	   /*
	    *    Ŭ���� => null
	    *    String => null
	    */
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   // 1. connection ��ü�� ������ �´� 
		   session=ssf.openSession();
		   total=session.selectOne("boardTotalPage");
	   }catch(Exception ex)
	   {
		    ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)//������ �Ǿ� �ִٸ� 
			   session.close(); // ������ ���� (�ڵ����� ��ȯ => ��ȯ �ٽ� ����� ����)
	   }
	   return total;
   }
   // ������ �߰� 
   //          resultType        parameterType
   public static void boardInsert(DataBoardVO vo)
   {
	   SqlSession session=null;
	   try
	   {
		   // �̸� ����� �� Connection
		   /*
		    *   autoCommit���� 
		    *   ssf.openSession(); // commit�� ���� ���
		    *   ssf.openSession(true);..autoCommit���� 
		    */
		   session=ssf.openSession(true);// commit�� �����Ѵ� 
		   // INSERT,UPDATE,DELETE => �����ͺ��̽� ���� => �ݵ�� COMMIT
		   session.insert("boardInsert",vo);
		   // �������� SQL������ �����ϸ� 
		   //session.commit();
	   }catch(Exception ex)
	   {
		   // ���� ó�� 
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null) // ����Ŭ�� ����Ǿ� �ִٸ� 
			   session.close();
	   }
   }
   // �󼼺��� 
   /*
    *   �󼼺��� => VO (VO:�Խù� �Ѱ��� ��ü ����)
    *   ���,ã�� => VO�� ������ => ArrayList
    *   no => Primary Key (�ߺ��� ���� ������)
    *   =================================================================
    *   ����Ŭ : ������ ���� (������) => ��� ������ �������� �� �� �ִ� ���� ���� => ����뵵 
    *          => �ʿ��� �����͸� ������ ������ SQL����  
    *   �ڹ� : ����Ŭ ,�� �����ؼ� ����ڰ� ��û�� ������ ������ ���� ���� => ������ ���� 
    *   JSP : ����� ��û�� �����͸� ��� 
    *   =================================================================
    *   ���ȿ��� ó�� (�ڹٷ� ����� �� ���� , �ڹٽ�ũ��Ʈ) : Modal(�˾�â), �Է��� �ȵ� ��� ó�� , �ǽð�
    */
   public static DataBoardVO boardDetailData(int no)
   {
	   // �����͸� ���� ���� ���� 
	   DataBoardVO vo=new DataBoardVO();
	   // ����Ŭ ���� 
	   SqlSession session=null;// Connection => SqlSession�ȿ� Connection����� ������ �ִ� 
	   // �����͸� ������ ���� ���� ������ �߻��� �� �ִ� => ������ �������� ���α׷� => ����ó�� 
	   /*
	    *    ����ó�� 
	    *     = ���� ó�� (������ �߻��ϸ� �ٷ� ó���ؼ� ���) try~catch
	    *     = ����ó�� (�ý��� �����߻����θ� Ȯ���ϰ� ���� ȸ��) throws 
	    */
	   try
	   {
		   // ���������� ���� => ���� => catch�� �����ϰ� ���� 
		   // ���� 
		   session=ssf.openSession(); // Commit�� �������� �ʴ´� 
		   // �۾� ���� 
		   // 1. ��ȸ�� ���� 
		   // <update id="hitIncrement" parameterType="int">
		   session.update("hitIncrement",no);
		   session.commit();// ������ �ȵǸ� ��ȸ���� ������ ���� �ʴ´� 
		   // 2. ������ ��ȸ���� �����ؼ� ������ ������ ����
		   // <select id="boardDetailData" resultType="DataBoardVO" parameterType="int">
		   vo=session.selectOne("boardDetailData", no);
		   /*
		    *   COMMIT => ����� �����Ͱ� ����,�߰� ,����  => �ٽ� ���� 
		    *             SELECT : �˻� 
		    */
		   /*
		    *   <select> 
		    *      = ���(������) => selectList("id��")
		    *      = �Ѱ� => selectOne("id��")
		    *   =================================
		    *   <insert>
		    *      = insert("id��")
		    *   <update>
		    *      = update("id��")
		    *   <delete>
		    *      = delete("id��")
		    *   ================================= �ݵ�� COMMITó���� �ؾߵȴ� 
		    */
	   }catch(Exception ex)
	   {
		   // ������ ���� ��쿡 ó�� (����) 
		   ex.printStackTrace();//� ������ ������ Ȯ�� 
	   }
	   finally
	   {
		   // ������� ,������ ���� ������� => ������ ���� (�������� ����,�����ͺ��̽� ���� ����)
		   if(session!=null) // ����Ǿ� �ִٸ�
			   session.close();  // ps.close(), conn.close() => disConnection()
	   }
	   
	   return vo;// ����ڰ� ��û�� �����͸� �޾� �� �� �ִ�
   }
   
   // ã�� ���� ���� 
   // ã�� ���� 
   // <select id="boardFindCount" resultType="int" parameterType="hashmap">
   public static int boardFindCount(Map map)
   {
	   int count=0;
	   // ����(����Ŭ) => ��ü
	   SqlSession session=null;
	   try
	   {
		   // ���� ��ü�ּ� ������ ����  : DBCP (�̸� ����Ŭ�� ������ Connection�� ���� => ������ �ּ�)
		   // default => connection (8��)
		   session=ssf.openSession();// commit�� �ʿ䰡 ���� (select)
		   // commit�� �ʿ��Ѱ��� �����ͺ��̽� ���� (insert,update,delete)
		   count=session.selectOne("boardFindCount",map);
	   }catch(Exception ex)
	   {
		   // ���� ó�� 
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close(); // conn.close() => DBCP (��ȯ=>����)
	   }
	   return count;
   }
   // <select id="boardFindData" resultType="DataBoardVO" parameterType="hashmap">
   // ���� ã�� ������ �б�
   public static List<DataBoardVO> boardFindData(Map map)
   {
	   List<DataBoardVO> list=new ArrayList<DataBoardVO>();
	   // ���� ��ü
	   SqlSession session=null;
	   try
	   {
		   // ��ü�ּ� ���
		   session=ssf.openSession(); // COMMIT(X)
		   list=session.selectList("boardFindData",map);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return list;
   }
   // ���� �ϱ� 
   // JSP(��û) ==> databoard-mapper.xml ==> DAO ó�� ==> JSP���� �޾Ƽ� ȭ�� ���
   public static boolean boardDelete(int no,String pwd)
   {
	   boolean bCheck=false;
	   SqlSession session=null;
	   try
	   {
		   // Conection
		   session=ssf.openSession();
		   // ��й�ȣ ������ ����
		   // <select id="boardGetPassword" resultType="String" parameterType="int">
		   String db_pwd=session.selectOne("boardGetPassword",no);
		   
		   if(pwd.equals(db_pwd))
		   {
			   bCheck=true;
			   // ���� ����
			   // <delete id="boardDelete" parameterType="int">
			   session.delete("boardDelete",no);
			   // �ݵ�� commit
			   session.commit();
		   }
		   else
		   {
			   bCheck=false;
		   }
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // session�� �ݴ´� (connection,preparedstatement ����)
		   if(session!=null)
			   session.close();
	   }
	   return bCheck;
   }
   // <select id="boardGetInfoData" resultType="DataBoardVO" parameterType="int">
   public static DataBoardVO boardGetInfoData(int no)
   {
	   DataBoardVO vo=new DataBoardVO();
	   SqlSession session=null;
	   try
	   {
		   // ���� 
		   session=ssf.openSession();
		   vo=session.selectOne("boardGetInfoData",no);
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
	   return vo;
   }
   // �����ϱ� ���� ������ �б� ����
   // <select id="boardDetailData" resultType="DataBoardVO" parameterType="int">
   // MyBatis ==> ����Ǿ� �ִ� SQL������ ������ ����� ���� 
   public static DataBoardVO boardUpdateData(int no)
   {
	   DataBoardVO vo=new DataBoardVO();
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();// ����
		   vo=session.selectOne("boardDetailData",no);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();// ���� ó��
	   }
	   finally
	   {
		   if(session!=null)
			   session.close(); // ��ȯ(������ �ٽ� ����� ����)
	   }
	   return vo;
   }
   // �����ϱ�
   public static boolean boardUpdate(DataBoardVO vo)
   {
	   boolean bCheck=false;
	   SqlSession session=null;
	   try
	   {
		   // ����Ŭ ���� 
		   session=ssf.openSession();
		   // ��й�ȣ �˻� 
		   // <select id="boardGetPassword" resultType="String" parameterType="int">
		   String db_pwd=session.selectOne("boardGetPassword",vo.getNo());
		   if(db_pwd.equals(vo.getPwd()))// �����̸� 
		   {
			   bCheck=true;
			   // ���� �����Ѵ�
			   session.update("boardUpdate",vo);
			   // �����Ѵ�
			   session.commit();
		   }
		   else //������ �ƴϸ� 
		   {
			   bCheck=false;
		   }
	   }catch(Exception ex)
	   {
		   // ���� ó��
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close(); // ���� ���� => �ٽ� ����ϱ� ���� ��ȯ
	   }
	   return bCheck;
   }
}







