<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
 <%
 	MusicDAO dao=new MusicDAO();
	ArrayList<MovieVO> list=dao.movieAllData();
 %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Movie</h1>
		<table border=1 width=1300>
			<tr>
				<th>순위</th>
				<th>제목</th>
				<th>포스터</th>
				<th>발매일</th>
				<th>장르</th>
				<th>등급</th>
			</tr>
			<%
				for(MovieVO vo:list)
				{
			%>
					<tr>
						<td><%=vo.getNo() %></td>
						<td><%=vo.getTitle() %></td>
						<td>
						<img src=<%=vo.getPoster() %> width=100 height=100>
						</td>
						<td><%=vo.getRegdate().toString() %></td>
						<td><%=vo.getGenre() %></td>
						<td><%=vo.getGrade() %></td>
					</tr>
			<%	
				}
			%>
		</table>
	</center>
</body>
</html>