<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String[] urls = request.getRequestURI().split("/");
	String lastUrl = urls[urls.length-1];
	String mUrl = lastUrl.replace(".jsp", ".do");
%>
<div class="row-fluid">
	<div class="span3">
		<div class="well sidebar-nav">
			<ul class="nav nav-list">
				<li><a href="<%=request.getContextPath() %>/logout.do">로그 아웃</a></li>
				<li class="nav-header">게시판 관리</li>
				<li <% if(mUrl.equals("boardAdd.do")){out.println("class='active'");}%>><a href="getBoardAdd.do">게시판 추가</a></li>
				<li <% if(mUrl.equals("boardModify.do")){out.println("class='active'");}%>><a href="getBoardModify.do">게시판 수정</a></li>
				<li <% if(mUrl.equals("boardDelete.do")){out.println("class='active'");}%>><a href="getBoardDelete.do">게시판 삭제</a></li>
				<li class="nav-header">게시판 글 관리</li>
				<li><a href="#">게시판 글 관리</a></li>
				<li class="nav-header">관리자 관리</li>
				<li><a href="#">관리자 추가</a></li>
				<li><a href="#">관리자 수정</a></li>
				<li><a href="#">관리자 삭제</a></li>
				<li class="nav-header">SNS설정</li>
				<li><a href="#">페이스북</a></li>
				<li><a href="#">트위터</a></li>
			</ul>
		</div>
	</div>