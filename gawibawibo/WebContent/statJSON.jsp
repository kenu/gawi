<%@page pageEncoding="utf-8"
	contentType="application/json; charset=utf-8"%><%@page
	import="net.okjsp.gawi.Play"
%><%
	Play play = new Play();
	play.load();
%>{"total":<%=play.getTotal()%>, 
"win":<%=play.getWin()%>, 
"even":<%=play.getEven()%>, 
"lose": <%=play.getLose()%>, 
"rate": "<%=play.getRate()%>%"}