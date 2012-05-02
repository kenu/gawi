<%@page pageEncoding="utf-8" %>
<%@page import="net.okjsp.gawi.Play"%>
<%
	Play play = new Play();
	play.load();
%>
<div>
TOTAL: <%= play.getTotal() %>
(<%= play.getWin() %>승 <%= play.getEven() %>무 <%= play.getLose() %>패
승률: <%= play.getRate() %>%)
</div>
