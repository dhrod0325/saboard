<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="inc/header.jsp"></jsp:include>
		<jsp:include page="inc/left_menu.jsp"></jsp:include>
		<div class="span9">
			<div class="hero-unit">
				<h1>게시판 관리</h1>
				<hr />
			</div>
			<div class="row-fluid">
				<div class="span4">
					<h2>게시판 목록</h2>
					<c:forEach items="${boardTableList }" var="boardTableDomain">
						<ul>
							<li><a href="<%=request.getContextPath() %>/getBoardListView.do?board_id=${boardTableDomain.board_id }">${boardTableDomain.board_nm }</a></li>
						</ul>					
					</c:forEach>
				</div>
				<div class="span4">
					<h2>스킨 목록</h2>
					<ul>
					<c:forEach items="${skinList }" var="skin">
						<li>${skin }</li>
					</c:forEach>
					</ul>
				</div>
				<div class="span4">
					<h2>위젯 3</h2>
					<p>무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			
			<div class="row-fluid">
				<div class="span4">
					<h2>위젯4</h2>
					<p>무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<!--/span-->
				<div class="span4">
					<h2>위젯 5</h2>
					<p>무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<!--/span-->
				<div class="span4">
					<h2>위젯 6</h2>
					<p>무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...무엇이 들어가면 괜찮을까...</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
		</div>
		<!--/span-->
	
	<!--/row-->
<jsp:include page="inc/footer.jsp"></jsp:include>