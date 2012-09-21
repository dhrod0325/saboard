<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../inc/header.jsp"></jsp:include>
<jsp:include page="../inc/left_menu.jsp"></jsp:include>
<div class="span9">
	<div class="row-fluid">
		<div class="span4">
			<h2 class="page-header">게시판 관리</h2>
			<form:form commandName="boardTableDomain">
				<fieldset>
					<legend>게시판 추가</legend>
					<label for="board_id">게시판 테이블명</label> 
					<form:input path="board_id" />
					<form:errors path="board_id" />
					<span class="help-block">영어와 _ 으로만 입력해주세요 예)abc_te</span>
					
					<label for="board_nm">게시판 명</label> 
					<form:input path="board_nm"/>
					<form:errors path="board_nm" />
					<span class="help-block">게시판 명을 입력해주세요 (한글 또는 영문)</span>
					
					<label for="board_desc">게시판 설명</label> 
					<form:textarea path="board_desc" rows="4" />
					<form:errors path="board_desc" />
					<span class="help-block">게시판 설명을 입력해주세요.</span>
					
					<label for="theme">게시판 테마</label> 
					<form:select path="theme" items="${skinList }"></form:select>
					
					<form:errors path="theme" />
					<div class="clearfix"></div>
					<button type="submit" class="btn">Submit</button>
				</fieldset>
			</form:form>
		</div>
		<div class="span8">
			<h2 class="page-header">게시판 목록</h2>
			<div class="row-fluid">
				<c:forEach items="${boardTableList }" var="boardTableDomain" varStatus="i">
					<div class="span4" style="margin-bottom:15px;">
						   <a href="<%=request.getContextPath() %>/getBoardListView.do?board_id=${boardTableDomain.board_id }">
							   <div class="thumbnail">
							      <h3>테이블 명 : <c:out value="${boardTableDomain.board_id }" escapeXml="true"></c:out></h3>
							      <p>게시판 명 : <c:out value="${boardTableDomain.board_nm }" escapeXml="true"></c:out> </p>
							      <p>테마 : <c:out value="${boardTableDomain.theme }" escapeXml="true"></c:out></p>
							      <p>설명 : <c:out value="${boardTableDomain.board_desc }" escapeXml="true"></c:out></p>
							   </div>
						   </a>
					</div>
					<c:if test="${i.count%3==0 }">
						</div>
						<div class="row-fluid">
					</c:if>				
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>