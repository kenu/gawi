<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>가위 바위 보</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="query.jsp"></jsp:include>
<jsp:include page="stat.jsp"></jsp:include>
<form method="post">
선택하세요:
<button name="choice" value="0">가위</button>
<button name="choice" value="1">바위</button>
<button name="choice" value="2">보</button>
</form>
</body>
</html>