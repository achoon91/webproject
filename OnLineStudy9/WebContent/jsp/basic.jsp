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
		<h1>���� ����</h1>
			<ul>
				<li>��������:<%=application.getServerInfo() %></li>
				<li>������:<%=application.getMajorVersion() %></li>
				<li>���̳�:<%=application.getMinorVersion() %></li>
			</ul>
	</center>
</body>
</html>