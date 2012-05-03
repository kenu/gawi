<%@page import="net.okjsp.gawi.Play"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	Play play = new Play();
	String schoice = request.getParameter("choice");
	if (schoice != null) {
		int choice = Integer.parseInt(schoice);
		int computerChoice = play.getComputerChoice();
		String judgement = play.judge(choice, computerChoice);
		play.save(choice, computerChoice, judgement);
%>
----
<strong>당신: <%= play.items[choice] %></strong>
컴퓨터: <%= play.items[computerChoice] %>
----
<div id="judgement">
<%= judgement %>
</div>
<%
	}
%>