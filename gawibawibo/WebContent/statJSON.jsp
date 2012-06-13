<%@page import="net.okjsp.gawi.Play"%>
<%@page import="net.okjsp.gawi.Stat"%>
<%@page pageEncoding="utf-8" %>
<%
	Play play = new Play();
	play.load();
	Stat stat = play.getStat();
%>{"total":<%=stat.getTotal()%>, 
"win":<%=stat.getWin()%>, 
"even":<%=stat.getEven()%>, 
"lose": <%=stat.getLose()%>, 
"rate": "<%=stat.getRate()%>%"}