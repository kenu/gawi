<%@page pageEncoding="utf-8" %>
<%@page import="net.okjsp.gawi.Play"%>
<%
	Play.load();
%>
<div>
TOTAL: <%= Play.getTotal() %>
(<%= Play.getWin() %>승 <%= Play.getEven() %>무 <%= Play.getLose() %>패
승률: <%= Play.getRate() %>%)
</div>
