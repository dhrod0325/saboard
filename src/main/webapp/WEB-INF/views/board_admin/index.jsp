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
					<h2>스킨 목록</h2>
					<ul>
					<c:forEach items="${skinList }" var="skin">
						<li>${skin }</li>
					</c:forEach>
					</ul>
				</div>
				<!--/span-->
				<div class="span4">
					<h2>게시판 2</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<!--/span-->
				<div class="span4">
					<h2>게시판 3</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			
			<div class="row-fluid">
				<div class="span4">
					<h2>Heading</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<!--/span-->
				<div class="span4">
					<h2>Heading</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<!--/span-->
				<div class="span4">
					<h2>Heading</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
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