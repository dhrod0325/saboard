<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/tlds/pagination.tld"%>
<jsp:include page="inc/header.jsp">
	<jsp:param value="글 상세보기" name="title"/>
</jsp:include>
<form:form name="thisForm" commandName="boardDomain">
	<form:hidden path="pageNo"/>
	<table class="table table-bordered table-hover">
		<tr>
			<th style="width:10%;">제목</th>
			<td colspan="3">${boardDomain.title }</td>
		</tr>
		<tr>
		 	<th>내용</th>
			<td colspan="3">${boardDomain.content }</td>
		</tr>
		<tr>
			<th style="width:10%;">파일 명</th>
			<td>
				${boardFileDomain.file_name }
			</td>
			<th style="width:10%;">
				파일 크기
			</th>
			<td>${boardFileDomain.file_size }</td>
		</tr>
	</table>
</form:form>
	<table class="table table-bordered table-hover">
		<tr>
			<th colspan="4">댓글</th>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" /></td>
			<th>E-MAIL</th>
			<td><input type="text" /></td>
		</tr>
		<tr>
			<th>비밀 번호</th>
			<td colspan="3"><input type="password" class="input-xxlarge" /></td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea name="" id="" rows="4" style="width:97%;"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="text-align-right">
				<a href="#" class="btn btn-primary">전송</a>
				<a href="#" id="btn_back" class="btn btn-primary">뒤 로</a>
			</td>
		</tr>
	</table>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn_back').click(function(e){
			e.preventDefault();
			
			$('#boardDomain').attr('action','getBoardListView.do');
			$('#boardDomain').submit();
		});		
	});
</script>
<jsp:include page="inc/footer.jsp"></jsp:include>