<%@page import="net.okjsp.gawi.Play"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>가위 바위 보</title>
<style type="text/css">
* {text-align: center;}
#judgement {background-color: #dde;}
form {border: 1px solid #ddd;}
</style>
</head>
<body>
<%
	String schoice = request.getParameter("choice");
	if (schoice != null) {
		int choice = Integer.parseInt(schoice);
		int computerChoice = Play.getComputerChoice();
		String judgement = Play.judge(choice, computerChoice);
		Play.save(choice, computerChoice, judgement);
		Play.load();
%>
----
<strong>당신: <%= Play.items[choice] %></strong>
컴퓨터: <%= Play.items[computerChoice] %>
----
<div id="judgement">
<%= judgement %>
</div>
<div>
TOTAL: <%= Play.getTotal() %>
(<%= Play.getWin() %>승 <%= Play.getEven() %>무 <%= Play.getLose() %>패
승률: <%= Play.getRate() %>%)
</div>
<%
	}
%>
<form>
선택하세요:
<input type="radio" name="choice" value="0" id="0"> <label for="0">가위</label>
<input type="radio" name="choice" value="1" id="1"> <label for="1">바위</label>
<input type="radio" name="choice" value="2" id="2"> <label for="2">보</label>
<input type="submit" value="내기">
</form>
</body>
</html>