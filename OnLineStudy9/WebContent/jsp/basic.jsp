<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>서버 정보</h1>
			<ul>
				<li>서버정보:<%=application.getServerInfo() %></li>
				<li>메이져:<%=application.getMajorVersion() %></li>
				<li>마이너:<%=application.getMinorVersion() %></li>
			</ul>
	</center>
</body>
</html>