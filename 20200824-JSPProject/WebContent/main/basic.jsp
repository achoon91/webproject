<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
		JSP : Java Server Page => 서버에서 실행되는 자바파일
		=====
		JSP의 구성요소
			1. 지시자
				= page : JSP파일에대한 정보
				= tablib : 태그로 자바의 문법
							===> 제어문 <c:forEach> <c:if>
				 자바 + HTML (HTML기반 자바가 첨부)
				 자바 영역 <% %>
				 <html>
				 	<body>
				 		<h1>게시판</h1>
				 		<ul>
				 			<c:forEach items="list">
				 				<li>번호-제목-이름-작성일-조회수</li>
				 			</c:forEach>
				 		</ul>
				 	</body>
				 </html>
				 
				= include : 틀정 JSP안에 JSP를 첨부
			
			2. 자바 코딩 부분
				= 스크립트릿 <% %> => 일반 자바 코딩
				= 표현식 <%= %> => out.println() : 화면에 값을 출력 
				= 선언시 <%! %> => 전역변수,메소드를 만들 경우 (사용빈도가 없다)
			
			3. 내장 객체 (8개 지원) : 미리 객체를 생성해 놓고 필요시마다 사용이 가능하게 만들어준다
				= request : 사용자의 요청값
				= response : 서버 => 처리 => 응답
				= session
				= pageContext
				= page
				= config
				= exception
				= application
			4. 표현식 (EL,JSTL)
			5. MVC
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String name="홍길동";
	out.println(name);
%>
</body>
</html>