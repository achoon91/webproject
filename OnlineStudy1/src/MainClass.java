
import java.sql.*;

public class MainClass {
   /*
    * 2.방법 1) 인라인 CSS <태크 style=""> 2) 내부 css <head> <style> </style> </head> ****
    * 3) 외부 css 파일제작 => <link rel="" href=""> 재사용 ***
    * 
    * 3.태크 => block 속성 : 태그가 전체화면을 차지하고 있다 사용시) 다음줄에 출력 System.out.println()
    * div,h1~h6..와 같다 => inline 속성 : System.out.print() a,img,input..
    * 
    */
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         String url="jdbc:oracle:thin:@211.238.142.181:1521:XE";
         Connection conn =DriverManager.getConnection(url,"hr","happy");
         String sql= "SELECT empno,ename,job FROM emp";
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         while(rs.next())
         {
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
         }
         rs.close();
      }catch (Exception e) {System.out.println(e.getMessage());}
   }
}