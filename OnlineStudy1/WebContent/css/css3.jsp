<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
span#bbb h1{
	background-color: green;
}
#aaa {
	background-color: blue;
}
#ccc h1{
	backgroud-color: orange;
}
</style>
<script type="text/javascript"
src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#ccc h1').click(function)(){
		alert("id:ccc h1를 클릭");
	});
});
</script>
</head>
<body>
	<h1 id="aaa">Java</h1>
	<span id="bbb">
		<h1>Oracle</h1>
		<h1>JSP</h1>
	</span>
	<span id="ccc">
		<h1>Spring</h1>
	</span>
</body>
</html>