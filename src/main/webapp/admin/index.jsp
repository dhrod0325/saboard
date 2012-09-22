<%@page import="kr.oks.saboard.core.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	SessionUtil.sendRedirect(request, response, "/admin/getAdmin.do");
%>
