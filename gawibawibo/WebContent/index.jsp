<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>가위 바위 보</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
var send = function(e) {
	$.post('query.jsp', { choice: this.value })
	 .success(function(data) {
		 $('#result').html(data); 
	  });
};

$(function(){
	$('form').bind('submit', function(e) {return false;});
	$('button').click(send);
});
</script>
</head>
<body>
<div id="result">
<jsp:include page="stat.jsp"></jsp:include>
</div>
<form method="post">
선택하세요:
<button name="choice" value="0">가위</button>
<button name="choice" value="1">바위</button>
<button name="choice" value="2">보</button>
</form>
</body>
</html>