<%@page import="net.okjsp.gawi.Play"%>
<%@ page language="java" pageEncoding="utf-8"%><%
	Play play = new Play();
	String schoice = request.getParameter("choice");
	if (schoice != null) {
		int choice = Integer.parseInt(schoice);
		int computerChoice = play.getComputerChoice();
		String judgement = play.judge(choice, computerChoice);
		play.save(choice, computerChoice, judgement);
%>{"p1":{"name":"당신", "choice":"<%= play.items[choice] %>"},
 "p2":{"name":"컴퓨터", "choice":"<%= play.items[computerChoice] %>"},
"judgement" : "<%= judgement %>"}
<%
	}
%>
